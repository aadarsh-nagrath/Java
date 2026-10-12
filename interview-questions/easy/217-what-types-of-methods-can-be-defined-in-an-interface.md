# 217. What types of methods can be defined in an interface?

| Type | Since | Has body? | Inherited / overridable? | Notes |
| --- | --- | --- | --- | --- |
| `abstract` (implicitly `public abstract`) | 1.0 | No | Must be implemented | The contract |
| `default` | 8 | Yes | Inherited, can be overridden | Evolve interfaces without breaking implementers |
| `static` | 8 | Yes | **Not inherited**; call as `Iface.method()` | Utility/factory methods, e.g. `List.of()` |
| `private` | 9 | Yes | No | Helper for default methods |
| `private static` | 9 | Yes | No | Helper for static methods |

```java
interface PaymentGateway {
    void pay(BigDecimal amount);                         // abstract
    default void refund(BigDecimal a) { log("refund"); } // default
    static PaymentGateway noop() { return a -> {}; }     // static
    private void log(String s) { System.out.println(s); } // private
}
```

Also:
- Fields are always `public static final` (constants).
- A **default** method can't override `Object` methods (`equals`, `hashCode`, `toString`). That's a compile error.
- An interface with exactly one abstract method is a **functional interface** (`@FunctionalInterface`) and can be implemented by a lambda.
- No constructors, no instance fields, no `protected` / `final` / `synchronized` methods.

---

[← Back to Easy index](README.md)
