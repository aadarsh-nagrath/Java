# 234. What is a Spring Profile, and how have you used it?

A **profile** is a named, logical group of beans and configuration that is only active in a given **environment** (dev, test, qa, prod). It lets one build artifact run everywhere with different settings.

**Profile-specific config files**
```
application.yml           # common
application-dev.yml       # H2, debug logging
application-prod.yml      # Postgres, secrets from env/Vault
```
```yaml
# application-prod.yml
spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST}:5432/orders
logging.level.root: WARN
```

**Profile-specific beans**
```java
@Configuration
class StorageConfig {
    @Bean @Profile("dev")   FileStorage localStorage() { return new LocalDiskStorage(); }
    @Bean @Profile("!dev")  FileStorage s3Storage()    { return new S3Storage(); }
}
```

**Activating**
- `spring.profiles.active=prod` in properties
- `SPRING_PROFILES_ACTIVE=prod` env var (typical in Docker/Kubernetes)
- `java -jar app.jar --spring.profiles.active=prod`
- Tests: `@ActiveProfiles("test")`
- Profile groups: `spring.profiles.group.prod=prod-db,prod-mq`
- Read it in code via `Environment.getActiveProfiles()`. Use `spring.profiles.default` as a fallback.

### "How have you used it?" — a sample answer
> "We had `dev`, `qa` and `prod` profiles. In `dev` we used H2 and a mock payment gateway bean (`@Profile("dev")`) so developers didn't need sandbox keys. QA and prod pointed to Postgres, with credentials injected as Kubernetes secrets. The active profile was set with `SPRING_PROFILES_ACTIVE` in each environment's Helm values. In integration tests we used `@ActiveProfiles("test")` with Testcontainers. We also disabled the Swagger UI bean outside `dev`/`qa`."

**Don't:** put secrets in `application-prod.yml` in git, or use profiles for feature flags (use a properties-backed flag instead).

---

[← Back to Easy index](README.md)
