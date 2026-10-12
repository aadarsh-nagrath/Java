# 211. What is the Optional class?

`java.util.Optional<T>` (Java 8) is a container that **may or may not hold a non-null value**. It makes "no result" explicit in a method's **return type**, so callers can't forget the null check.

```java
Optional<User> findByEmail(String email);

String name = repo.findByEmail(e)
        .filter(User::isActive)
        .map(User::getName)
        .orElse("guest");

User u = repo.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
```

| Method | Use |
| --- | --- |
| `Optional.of(x)` / `ofNullable(x)` / `empty()` | Create (`of(null)` throws NPE) |
| `isPresent()` / `isEmpty()` (11) | Check |
| `ifPresent(c)` / `ifPresentOrElse(c, r)` (9) | Act |
| `map` / `flatMap` / `filter` | Transform |
| `orElse(v)` | Default. **Always evaluated**, even if a value is present |
| `orElseGet(supplier)` | Lazy default. Use it when the default is expensive |
| `orElseThrow()` / `orElseThrow(supplier)` | Fail fast |
| `or(supplier)` (9), `stream()` (9) | Chain / flatten |

**Best practices**
- Use it **only as a return type**. Not for fields (not `Serializable`), method parameters, or collections (return an empty list instead).
- Never call `get()` without checking. Prefer `orElseThrow()`.
- Never return `null` from a method declared to return `Optional`.
- Use `OptionalInt` / `OptionalLong` / `OptionalDouble` for primitives to avoid boxing.

---

[← Back to Easy index](README.md)
