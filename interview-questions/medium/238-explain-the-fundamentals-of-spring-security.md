# 238. Explain the fundamentals of Spring Security.

Spring Security is a **filter-based** framework for **authentication** (*who are you?*) and **authorization** (*what may you do?*), plus protection against common attacks (CSRF, session fixation, clickjacking, security headers).

### Architecture
```
HTTP request
   → DelegatingFilterProxy           (servlet filter, bridges to Spring)
   → FilterChainProxy                (picks the matching SecurityFilterChain)
   → SecurityFilterChain: ordered filters
        CorsFilter → CsrfFilter → LogoutFilter
        → UsernamePasswordAuthenticationFilter / BearerTokenAuthenticationFilter / your JwtFilter
        → ExceptionTranslationFilter → AuthorizationFilter
   → DispatcherServlet → Controller
```

### Authentication flow
1. A filter extracts credentials into an `Authentication` token (unauthenticated).
2. `AuthenticationManager` (implementation: `ProviderManager`) delegates to an `AuthenticationProvider`.
3. `DaoAuthenticationProvider` loads the user via **`UserDetailsService.loadUserByUsername()`** and checks the password with a **`PasswordEncoder`** (BCrypt).
4. On success, the authenticated `Authentication` (principal + `GrantedAuthority` roles) is stored in **`SecurityContextHolder`** (a `ThreadLocal`), and in the HTTP session for stateful apps.
5. On failure → `AuthenticationEntryPoint` → **401**. Authenticated but not allowed → `AccessDeniedHandler` → **403**.

### Authorization
- URL-based in the filter chain: `.requestMatchers("/admin/**").hasRole("ADMIN")`.
- Method-based: `@EnableMethodSecurity` + `@PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")`.

### Configuration (Spring Security 6 / Boot 3; `WebSecurityConfigurerAdapter` was removed)
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())                     // only for stateless token APIs
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/actuator/health").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .build();
    }

    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
```

**Key terms:** Principal, Credentials, `GrantedAuthority` / Role (`ROLE_` prefix), `UserDetails`, `SecurityContext`, stateful (session + `JSESSIONID`) vs stateless (JWT), OAuth2 / OIDC.

---

[← Back to Medium index](README.md)
