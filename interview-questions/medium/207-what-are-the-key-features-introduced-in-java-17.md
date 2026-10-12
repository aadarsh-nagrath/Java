# 207. What are the key features introduced in Java 17?

Java 17 (Sept 2021) is an **LTS** release. Most "Java 17 features" landed in 12–16 and were finalized by 17, so interviewers usually mean *everything since Java 11*.

| Feature | Since | One-liner |
| --- | --- | --- |
| **Sealed classes** | 17 (final) | `sealed interface Shape permits Circle, Square` — restrict who can extend |
| **Records** | 16 | `record Point(int x, int y) {}` — immutable data carrier |
| **Pattern matching for `instanceof`** | 16 | `if (o instanceof String s) s.length();` — no cast |
| **Text blocks** | 15 | Multi-line string literals in triple quotes |
| **Switch expressions** | 14 | `int n = switch (d) { case MON -> 1; default -> 0; };` |
| **Helpful NullPointerExceptions** | 14 | Message says *which* variable was null |
| `Stream.toList()` | 16 | Unmodifiable list, shorter than `collect(Collectors.toList())` |
| New `RandomGenerator` API | 17 | Common interface for all PRNGs |
| Strong encapsulation of JDK internals | 17 | `--illegal-access` gone; `sun.misc.*` internals blocked |
| Pattern matching for `switch` | 17 (preview) | Final in Java 21 |
| Deprecations / removals | 17 | Security Manager deprecated, Applet API deprecated for removal, RMI Activation removed |

```java
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}

double area(Shape s) {
    if (s instanceof Circle c) return Math.PI * c.r() * c.r();
    if (s instanceof Square sq) return sq.side() * sq.side();
    throw new IllegalStateException();
}
```

> Tip: mention that **Java 21** (virtual threads, record patterns, pattern-matching `switch`, sequenced collections) and **Java 25** are the newer LTS releases. It shows you're up to date.

---

[← Back to Medium index](README.md)
