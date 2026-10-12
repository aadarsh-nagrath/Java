# 209. What is an effectively final variable in Java?

A local variable (or parameter) is **effectively final** if it is **not declared `final` but is never reassigned** after initialization. The compiler treats it as if it were `final` (Java 8+).

It matters because **lambdas, anonymous classes and local classes can only capture local variables that are final or effectively final**.

```java
String prefix = "Hi ";                // effectively final
list.forEach(n -> System.out.println(prefix + n));   // ✅

int count = 0;
list.forEach(n -> count++);           // ❌ "must be final or effectively final"
```

**Why the rule?** A lambda captures a *copy* of the value, and the lambda may run later or on another thread. If the variable could still change, the copy and the original would silently disagree. Java avoids that by forbidding reassignment.

Workarounds when you really need to mutate: `AtomicInteger`, a one-element array, or (better) restructure using `stream().count()` / `reduce`.

Note: "effectively final" restricts the **reference**, not the object. `List<String> l = new ArrayList<>(); lambda -> l.add("x")` is fine.

---

[← Back to Easy index](README.md)
