# 240. How did you implement Spring Security across distributed microservices?

In microservices you **centralise authentication** and **decentralise authorization**. Using **OAuth2 / OpenID Connect + JWT** means each service can validate tokens on its own, without a shared session or a call to the auth server on every request.

### Architecture
```
Client (SPA / mobile)
   │  1. login (OIDC Authorization Code + PKCE)
   ▼
Authorization Server (Keycloak / Okta / Auth0 / Spring Authorization Server)
   │  2. issues signed JWT access token (+ refresh token); publishes public keys at /jwks
   ▼
API Gateway (Spring Cloud Gateway)
   │  3. validates JWT, rate limits, CORS, routes; forwards the token
   ▼
order-service ──(token relay / client-credentials)──► payment-service
   4. each service is an OAuth2 Resource Server: verifies signature with cached JWKS,
      checks exp/iss/aud, maps claims → roles, enforces @PreAuthorize
```

### Implementation
**Each microservice, a resource server**
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://auth.company.com/realms/shop     # JWKS discovered and cached
```
```java
@Bean
SecurityFilterChain api(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(a -> a
            .requestMatchers("/actuator/health").permitAll()
            .requestMatchers("/orders/**").hasAuthority("SCOPE_orders.read")
            .anyRequest().authenticated())
        .oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
}
```
Use a `JwtAuthenticationConverter` to map custom claims (e.g. Keycloak `realm_access.roles`) to `ROLE_*` authorities.

**Gateway.** Spring Cloud Gateway + `spring-boot-starter-oauth2-client` (for login, the BFF pattern) or a resource server (for token validation). Use the `TokenRelay` filter to pass the token downstream. Centralise rate limiting, CORS and request IDs here, but **services still validate tokens themselves** (zero trust: don't rely only on the gateway).

**Service-to-service calls**
- *On behalf of the user*: propagate the incoming bearer token (Feign `RequestInterceptor` / `WebClient` filter with `ServerOAuth2AuthorizedClientExchangeFilterFunction`).
- *Machine-to-machine* (batch jobs, events): the **client credentials** grant. The service gets its own token with limited scopes.
- Optionally **mTLS** via a service mesh (Istio/Linkerd) for transport identity, plus network policies.

**Cross-cutting concerns**
- Short-lived access tokens (5–15 min) + refresh tokens. Revocation via short TTL, a token denylist in Redis, or opaque tokens with introspection for sensitive operations.
- Validate `iss`, `aud`, `exp`, and the algorithm. Rotate keys through JWKS.
- Propagate a correlation ID and the user ID (from claims) for audit logs.
- Secrets (client secrets, keys) in Vault or Kubernetes secrets, never in config repos.
- Event-driven calls (Kafka): put the user/tenant context in message headers, and secure the broker itself with SASL/mTLS and ACLs.

### Sample answer
> "We used Keycloak as the identity provider. The Angular app logged in with Authorization Code + PKCE. Spring Cloud Gateway validated the JWT and relayed it downstream. Every service was a Spring Security resource server configured with `issuer-uri`, so it verified tokens locally using cached JWKS keys, with no network call per request. Roles came from Keycloak realm roles mapped through a custom `JwtAuthenticationConverter`, and fine-grained checks used `@PreAuthorize`. For internal calls between order and inventory services we used a Feign interceptor to forward the user's token, and for scheduled jobs a client-credentials client with its own scopes."

---

[← Back to Hard index](README.md)
