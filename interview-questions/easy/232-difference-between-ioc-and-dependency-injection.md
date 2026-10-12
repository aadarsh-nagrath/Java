# 232. Difference between IoC and Dependency Injection.

- **IoC (Inversion of Control)** is a **principle**: control of object creation and program flow is moved from your code to a framework/container. *"Don't call us, we'll call you."*
- **DI (Dependency Injection)** is a **pattern, one way to implement IoC**: an object's dependencies are *supplied from outside* (via constructor, setter or field) instead of the object creating or looking them up itself.

```java
// No IoC: the class controls its dependencies
class OrderService {
    private final PaymentGateway gw = new StripeGateway();   // tightly coupled, hard to test
}

// IoC via DI: the container creates StripeGateway and passes it in
@Service
class OrderService {
    private final PaymentGateway gw;
    OrderService(PaymentGateway gw) { this.gw = gw; }
}
```

| | IoC | DI |
| --- | --- | --- |
| What | Design principle | Design pattern / technique |
| Scope | Broad: who controls flow and lifecycle | Narrow: how dependencies are provided |
| Other implementations | DI, Service Locator, Template Method, event callbacks, strategy | — |
| In Spring | `ApplicationContext` is the IoC container | `@Autowired` / constructor injection |

**One-line answer:** *IoC is the "what" (hand control to the container); DI is the "how" (the container injects dependencies).* All DI is IoC, but not all IoC is DI. A servlet container calling your `doGet()` is IoC without DI.

---

[← Back to Easy index](README.md)
