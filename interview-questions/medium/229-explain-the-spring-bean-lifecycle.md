# 229. Explain the Spring Bean Lifecycle.

```
Container started
  │
  ├─ 1. Instantiate              (constructor; constructor injection here)
  ├─ 2. Populate properties      (@Autowired fields / setters, @Value)
  ├─ 3. Aware interfaces         BeanNameAware.setBeanName()
  │                              BeanClassLoaderAware, BeanFactoryAware
  │                              ApplicationContextAware.setApplicationContext()
  ├─ 4. BeanPostProcessor.postProcessBeforeInitialization()
  ├─ 5. @PostConstruct
  ├─ 6. InitializingBean.afterPropertiesSet()
  ├─ 7. Custom init method       (@Bean(initMethod = "init"))
  ├─ 8. BeanPostProcessor.postProcessAfterInitialization()   ← AOP proxy created here
  │
  ├─ ✅ Bean ready, in use
  │
Container shutting down (context.close())
  ├─ 9.  @PreDestroy
  ├─ 10. DisposableBean.destroy()
  └─ 11. Custom destroy method   (@Bean(destroyMethod = "cleanup"))
```

```java
@Component
public class CacheLoader implements InitializingBean, DisposableBean, BeanNameAware {
    private final ProductRepository repo;
    public CacheLoader(ProductRepository repo) { this.repo = repo; }      // 1

    public void setBeanName(String name) { log.info("name={}", name); }   // 3
    @PostConstruct void warmUp() { /* load cache */ }                       // 5
    public void afterPropertiesSet() { }                                   // 6
    @PreDestroy void flush() { /* persist */ }                              // 9
    public void destroy() { }                                              // 10
}
```

**Key points**
- Prefer **`@PostConstruct` / `@PreDestroy`** (from `jakarta.annotation`). `InitializingBean`/`DisposableBean` couple your code to Spring.
- Dependencies are **already injected** in `@PostConstruct`, but not in the constructor if you use field injection.
- **Prototype beans:** Spring runs the init callbacks but **never calls destroy callbacks**. The client owns them.
- Use a `BeanPostProcessor` to apply logic to *all* beans (e.g. custom annotations, wrapping in proxies).
- For logic that needs the *whole* app ready (e.g. Kafka consumers), listen for `ApplicationReadyEvent` or implement `SmartLifecycle` instead of `@PostConstruct`.

---

[← Back to Medium index](README.md)
