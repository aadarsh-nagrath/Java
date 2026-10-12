# 219. Explain the SOLID principles.

Five object-oriented design principles (Robert C. Martin) for code that is easy to change.

**S, Single Responsibility.** A class should have *one reason to change*.
```java
// ❌ InvoiceService calculates, saves to DB AND sends email
// ✅ InvoiceCalculator, InvoiceRepository, InvoiceMailer
```

**O, Open/Closed.** Open for extension, closed for modification. Add behaviour by adding code, not by editing working code.
```java
interface DiscountPolicy { BigDecimal apply(BigDecimal amt); }
class FestiveDiscount implements DiscountPolicy { ... }   // new type, no if/else edit
```

**L, Liskov Substitution.** Subtypes must be usable anywhere the parent is expected *without breaking correctness*. Example violation: `Square extends Rectangle`, where `setWidth` also changes height. See [220](220-explain-the-liskov-substitution-principle-if-a-subclass-can.md).

**I, Interface Segregation.** Clients shouldn't depend on methods they don't use. Prefer small, focused interfaces.
```java
// ❌ interface Worker { work(); eat(); }  → RobotWorker forced to implement eat()
// ✅ interface Workable { work(); }  interface Eatable { eat(); }
```

**D, Dependency Inversion.** High-level modules depend on **abstractions**, not concrete classes. Spring DI is built on this.
```java
class OrderService {
    private final PaymentGateway gateway;          // interface, not StripeGateway
    OrderService(PaymentGateway gateway) { this.gateway = gateway; }
}
```

**Payoff:** easier unit testing (mock the abstractions), smaller blast radius for changes, and less merge conflict. **Caveat:** don't over-apply them. One interface per class "just because" is over-engineering.

---

[← Back to Medium index](README.md)
