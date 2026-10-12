# 237. What are Fetch Types? Explain Lazy vs Eager Loading.

**Fetch type** (JPA/Hibernate) decides **when associated entities are loaded** from the database.

| | `FetchType.EAGER` | `FetchType.LAZY` |
| --- | --- | --- |
| When loaded | Immediately with the parent (join or extra select) | Only when first **accessed** (`order.getItems().size()`) |
| How | Real objects | **Proxy** / `PersistentBag` placeholder |
| Default for | `@ManyToOne`, `@OneToOne` | `@OneToMany`, `@ManyToMany` |
| Risk | Loads too much data; hidden joins; can't be "turned off" per query | `LazyInitializationException`; N+1 queries |

```java
@Entity
class Order {
    @ManyToOne(fetch = FetchType.LAZY)        // override the EAGER default (recommended)
    private Customer customer;

    @OneToMany(mappedBy = "order")            // LAZY by default
    private List<OrderItem> items;
}
```

**`LazyInitializationException`**: you access a lazy association **after the session/transaction has closed** (e.g. in the controller or during JSON serialization). Fixes: fetch what you need inside the transaction, use `JOIN FETCH`/entity graphs, or map to a DTO. Don't "fix" it by switching to EAGER or enabling `hibernate.enable_lazy_load_no_trans`.

**N+1 problem**: loading 100 orders, then touching `order.getCustomer()` for each one, runs 1 + 100 queries. Fixes:
```java
@Query("select o from Order o join fetch o.customer where o.status = :s")
List<Order> findWithCustomer(@Param("s") Status s);

@EntityGraph(attributePaths = {"customer", "items"})
List<Order> findByStatus(Status s);
```
Or set `hibernate.default_batch_fetch_size=50` (loads lazy collections in batches with `IN (...)`), or use **DTO projections**.

**Best practice:** make **everything LAZY** and decide per use case with `JOIN FETCH`, `@EntityGraph` or projections. Also consider `spring.jpa.open-in-view=false` (Boot warns about it) so lazy loading doesn't silently run queries during view rendering.

---

[← Back to Medium index](README.md)
