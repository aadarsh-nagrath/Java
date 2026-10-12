# 228. Explain the internal working of the Spring Container.

The **Spring IoC container** (`BeanFactory`, or more commonly `ApplicationContext`, which adds events, i18n, AOP integration and environment/profiles) creates, wires and manages **beans**. In Spring Boot, `SpringApplication.run()` creates the context and calls **`refresh()`**, where everything happens:

**1. Load bean definitions (the "recipes", not objects yet)**
- Sources: `@ComponentScan` (classes annotated `@Component`, `@Service`, `@Repository`, `@Controller`), `@Configuration` + `@Bean` methods, `@Import`, and **auto-configuration**.
- Each becomes a `BeanDefinition` (class, scope, dependencies, init/destroy methods, lazy flag) stored in the `BeanDefinitionRegistry` (a map keyed by bean name).

**2. Run `BeanFactoryPostProcessor`s.** These modify *definitions* before any bean exists.
- `ConfigurationClassPostProcessor` parses `@Configuration`, `@ComponentScan`, `@Import` and `@Bean`.
- `PropertySourcesPlaceholderConfigurer` resolves `${...}` placeholders.

**3. Register `BeanPostProcessor`s.** These hook into every bean's creation (e.g. `AutowiredAnnotationBeanPostProcessor` for `@Autowired`/`@Value`, `CommonAnnotationBeanPostProcessor` for `@PostConstruct`, and the AOP auto-proxy creator).

**4. Instantiate all non-lazy singletons** (`preInstantiateSingletons`). For each bean, `getBean()` → `createBean()`:
   1. **Instantiate**: constructor via reflection (constructor injection resolves dependencies first, recursively).
   2. **Populate properties**: field and setter injection.
   3. **Aware callbacks**: `BeanNameAware`, `ApplicationContextAware`, …
   4. `BeanPostProcessor.postProcessBeforeInitialization` → `@PostConstruct`.
   5. `afterPropertiesSet()` / custom `init-method`.
   6. `BeanPostProcessor.postProcessAfterInitialization`. **This is where AOP proxies are created** (`@Transactional`, `@Async`, `@Cacheable`), so other beans receive the *proxy*, not the raw object.
   7. Stored in the **singleton cache** (`singletonObjects` map).

**5. Finish.** The context publishes `ContextRefreshedEvent`, the embedded web server (Tomcat) starts, and then `ApplicationReadyEvent` fires.

**Other internals worth mentioning**
- **Circular dependencies**: for setter/field injection Spring uses a *three-level cache* (`singletonObjects`, `earlySingletonObjects`, `singletonFactories`) to expose an early reference. Constructor-injection cycles fail. Since Boot 2.6, circular references are **disabled by default**.
- **Scopes**: singleton (default, one per container), prototype (new on every `getBean`, and Spring doesn't manage its destruction), request, session, application.
- `@Configuration` classes are CGLIB-proxied so that calling one `@Bean` method from another returns the same singleton.
- On shutdown `close()` destroys singletons in reverse dependency order (`@PreDestroy`).

---

[← Back to Hard index](README.md)
