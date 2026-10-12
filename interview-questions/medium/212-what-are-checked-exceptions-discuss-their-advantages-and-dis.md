# 212. What are checked exceptions? Discuss their advantages and disadvantages.

**Checked exceptions** are subclasses of `Exception` (but not `RuntimeException`) that the **compiler forces you to handle** with `try/catch` or declare with `throws`. Examples: `IOException`, `SQLException`, `InterruptedException`. They model *recoverable, external* failures.

**Advantages**
- **Self-documenting API**: the signature tells callers what can go wrong.
- **Compiler-enforced**: you can't silently forget to handle a failure.
- Encourages thinking about recovery (retry, fallback, user message).

**Disadvantages**
- **Boilerplate**: `try/catch` everywhere, or `throws` clauses spreading up the call stack.
- **Leaky abstraction**: `UserRepository.find()` throwing `SQLException` exposes implementation details. Changing implementations changes the signature.
- **Bad with lambdas/streams**: `Function` can't throw checked exceptions, so you end up wrapping them.
- Leads to **anti-patterns**: empty `catch {}` blocks, or `catch (Exception e)` that swallows errors.
- Versioning pain: adding a new checked exception breaks every caller.

**Industry practice**: Spring, Hibernate and most modern libraries use **unchecked** exceptions (`DataAccessException` wraps `SQLException`). Kotlin and C# have no checked exceptions at all. A common rule: *checked if the caller can reasonably recover, unchecked for programming errors or failures nobody can fix locally*. When you can't recover, wrap and rethrow with the cause preserved:

```java
try { Files.readString(path); }
catch (IOException e) { throw new UncheckedIOException("Cannot read " + path, e); }
```

See also: [159. Checked vs Unchecked](159-what-is-the-difference-between-checked-exception-and-uncheck.md).

---

[← Back to Medium index](README.md)
