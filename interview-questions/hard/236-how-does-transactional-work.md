# 236. How does `@Transactional` work?

`@Transactional` gives **declarative transaction management** using **Spring AOP proxies**.

**Mechanism**
1. At startup, a `BeanPostProcessor` sees `@Transactional` and wraps the bean in a **proxy** (a JDK dynamic proxy for interfaces, otherwise CGLIB). Other beans get the proxy injected.
2. When a call reaches the proxy, `TransactionInterceptor` asks the `PlatformTransactionManager` (`JpaTransactionManager`, `DataSourceTransactionManager`) to **begin** a transaction: it gets a connection, sets `autoCommit=false`, and binds the connection/EntityManager to the **current thread** (`TransactionSynchronizationManager`, a `ThreadLocal`).
3. Your method runs. All repositories on that thread reuse the same connection.
4. On normal return → **commit** (JPA flushes dirty entities first). On exception → **rollback** (per the rollback rules).

```java
@Service
public class TransferService {
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 5)
    public void transfer(Long from, Long to, BigDecimal amt) {
        accountRepo.debit(from, amt);
        accountRepo.credit(to, amt);      // fails → debit is rolled back too
    }
}
```

**Attributes**
- **propagation**: `REQUIRED` (default: join the existing tx or create one), `REQUIRES_NEW` (suspend the outer tx and start a new one, e.g. audit logs), `NESTED` (savepoint), `SUPPORTS`, `MANDATORY`, `NOT_SUPPORTED`, `NEVER`.
- **isolation**: `READ_UNCOMMITTED`, `READ_COMMITTED`, `REPEATABLE_READ`, `SERIALIZABLE` (default = the DB's own default).
- **rollbackFor / noRollbackFor**: by default it rolls back only on **unchecked exceptions (`RuntimeException`) and `Error`**, **not checked exceptions**.
- **readOnly = true**: hint for optimisations (Hibernate skips dirty checking; some drivers route to replicas).
- **timeout**.

**Common pitfalls**
- **Self-invocation:** `this.otherTransactionalMethod()` bypasses the proxy, so no transaction starts. Move the method to another bean or inject self.
- **Visibility:** annotate `public` methods (Spring 6 class-based proxies also support protected/package-private methods, but never `private` or `final` ones).
- **Swallowing exceptions:** catching inside the method means no rollback. Rethrow, or call `TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()`.
- **Checked exceptions** don't roll back unless you use `rollbackFor`.
- **New threads** (`@Async`, `CompletableFuture`) don't share the transaction, because it is bound to the thread.
- Long transactions holding DB locks while making remote HTTP calls. Keep transactions short.
- Put it on the **service layer**, not controllers.

---

[← Back to Hard index](README.md)
