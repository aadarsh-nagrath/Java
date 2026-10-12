# 239. How have you implemented Spring Security in your project?

A typical **stateless JWT** setup for a REST API. Adapt the story to your own project.

### Flow
1. `POST /api/auth/login` with username/password → `AuthenticationManager` validates them against the DB (`UserDetailsService` + BCrypt).
2. On success, the server issues a short-lived **access token** (JWT, ~15 min, signed) and a **refresh token** (longer-lived, stored/rotated server-side).
3. The client sends `Authorization: Bearer <jwt>` on every request.
4. A filter validates the signature and expiry, builds an `Authentication` with the roles from the claims, and puts it in the `SecurityContextHolder`. No DB lookup and no session.
5. URL rules plus `@PreAuthorize` enforce roles. `@RestControllerAdvice`, `AuthenticationEntryPoint` and `AccessDeniedHandler` return clean 401/403 JSON.

### Code
```java
@Component
@RequiredArgsConstructor
class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwt;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            try {
                Claims c = jwt.parse(header.substring(7));            // verifies signature + exp
                List<SimpleGrantedAuthority> roles = c.get("roles", List.class).stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r)).toList();
                var auth = new UsernamePasswordAuthenticationToken(c.getSubject(), null, roles);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JwtException e) {
                SecurityContextHolder.clearContext();                 // invalid → stays anonymous → 401
            }
        }
        chain.doFilter(req, res);
    }
}

@Bean
SecurityFilterChain api(HttpSecurity http, JwtAuthFilter jwtFilter) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(a -> a
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

@Bean
AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
}
```

> Simpler alternative worth mentioning: instead of a hand-written filter, use Spring's built-in resource server, `http.oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()))` with `spring.security.oauth2.resourceserver.jwt.jwk-set-uri`. Spring then validates tokens for you.

### Hardening points to mention
- Passwords stored with **BCrypt**. Signing key from env/Vault (RS256 with key rotation preferred over a shared HS256 secret).
- Short access-token TTL. Refresh tokens rotated and revocable (stored in DB/Redis). Logout = revoke the refresh token.
- No sensitive data in the JWT payload (it's only base64-encoded, not encrypted).
- CORS restricted to known origins. HTTPS only. Rate limiting on `/login`.
- Method security (`@PreAuthorize`) for ownership checks, e.g. users can only read their own orders.
- Tests with `@WithMockUser` and `spring-security-test`.

---

[← Back to Hard index](README.md)
