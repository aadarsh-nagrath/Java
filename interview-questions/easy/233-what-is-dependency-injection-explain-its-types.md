# 233. What is Dependency Injection? Explain its types.

**Dependency Injection**: an object receives the objects it depends on from an external source (the Spring container) instead of creating them. Benefits: **loose coupling**, easy **unit testing** with mocks, swappable implementations, and centrally managed lifecycles.

### 1. Constructor injection (✅ recommended)
```java
@Service
public class OrderService {
    private final PaymentGateway gateway;
    private final OrderRepository repo;

    public OrderService(PaymentGateway gateway, OrderRepository repo) {   // @Autowired optional (single ctor, Spring 4.3+)
        this.gateway = gateway;
        this.repo = repo;
    }
}
```
- Dependencies can be `final`, so the object is immutable and always fully initialised.
- Required dependencies are obvious, and testable with `new OrderService(mockGw, mockRepo)`.
- Circular dependencies fail fast at startup (a good thing).
- Too many parameters signals the class does too much (SRP).
- With Lombok: `@RequiredArgsConstructor`.

### 2. Setter injection (for optional or changeable dependencies)
```java
private NotificationService notifier;

@Autowired(required = false)
public void setNotifier(NotificationService notifier) { this.notifier = notifier; }
```
- Good for optional dependencies or reconfiguration. Fields can't be `final`, and the object may be in a partially initialised state.

### 3. Field injection (❌ avoid in production code)
```java
@Autowired private PaymentGateway gateway;
```
- Concise, but hides dependencies, prevents `final`, and needs reflection or a Spring context to test. IntelliJ warns: *"Field injection is not recommended."* Acceptable in tests (`@Autowired MockMvc mvc`).

| | Constructor | Setter | Field |
| --- | --- | --- | --- |
| Immutability (`final`) | ✅ | ❌ | ❌ |
| Mandatory deps enforced | ✅ | ❌ | ❌ |
| Unit test without Spring | ✅ | ✅ | ❌ |
| Optional deps | via `Optional<T>`/`ObjectProvider<T>` | ✅ | ✅ |

(Some texts also list *interface injection*. Spring doesn't use it.)

---

[← Back to Easy index](README.md)
