# 231. What is `@Qualifier`? How does it differ from `@Primary`?

Both resolve the **`NoUniqueBeanDefinitionException`** that occurs when several beans match the type being injected.

```java
interface PaymentGateway {}
@Component("stripe") @Primary class StripeGateway implements PaymentGateway {}
@Component("razorpay")        class RazorpayGateway implements PaymentGateway {}

@Service
class CheckoutService {
    CheckoutService(PaymentGateway gw) { }                                   // → StripeGateway (@Primary)
}

@Service
class IndiaCheckoutService {
    IndiaCheckoutService(@Qualifier("razorpay") PaymentGateway gw) { }        // → RazorpayGateway
}
```

| | `@Primary` | `@Qualifier` |
| --- | --- | --- |
| Where | On the **bean definition** | At the **injection point** (also on the bean, to give it a qualifier) |
| Meaning | "Use me by default when there are several" | "I want *this specific* bean" |
| Who decides | The bean provider | The consumer |
| Precedence | Lower | **Higher**: `@Qualifier` overrides `@Primary` |
| How many | One per type, or still ambiguous | Any number |

**Other options**
- Inject **all** of them: `List<PaymentGateway>` or `Map<String, PaymentGateway>` (keyed by bean name), as in the factory/strategy pattern.
- Fallback by name: if the parameter name matches a bean name, Spring uses it.
- Custom qualifier annotations (`@Qualifier` meta-annotated `@Stripe`) for type safety instead of strings.

---

[← Back to Easy index](README.md)
