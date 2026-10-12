# 230. What does `@EnableAutoConfiguration` do?

It tells Spring Boot to **automatically configure beans based on the classpath, existing beans and properties**. For example, if `spring-boot-starter-data-jpa` and an H2/Postgres driver are on the classpath, you get a `DataSource`, `EntityManagerFactory` and `TransactionManager` without writing any config.

It's included in **`@SpringBootApplication`** = `@SpringBootConfiguration` + `@EnableAutoConfiguration` + `@ComponentScan`.

**How it works**
1. `@EnableAutoConfiguration` → `@Import(AutoConfigurationImportSelector.class)`.
2. The selector reads candidate configuration classes from
   `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (Boot 2.7+/3.x; older versions used `META-INF/spring.factories`) inside every jar.
3. Each candidate is a `@AutoConfiguration` class guarded by **conditions**, and only applies if the conditions match:

```java
@AutoConfiguration
@ConditionalOnClass(DataSource.class)                  // library present?
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class DataSourceAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean                          // back off if the user defined one
    DataSource dataSource(DataSourceProperties p) { ... }
}
```

Common conditions: `@ConditionalOnClass`, `@ConditionalOnMissingClass`, `@ConditionalOnBean`, `@ConditionalOnMissingBean`, `@ConditionalOnProperty`, `@ConditionalOnWebApplication`.

**Key idea: your beans win.** Auto-configs use `@ConditionalOnMissingBean`, so defining your own bean overrides the default.

**Useful in practice**
- Exclude one: `@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)` or `spring.autoconfigure.exclude=...`.
- See what applied and why: run with `--debug` (Condition Evaluation Report) or the `/actuator/conditions` endpoint.
- Write your own starter by registering a class in the `.imports` file.

---

[← Back to Medium index](README.md)
