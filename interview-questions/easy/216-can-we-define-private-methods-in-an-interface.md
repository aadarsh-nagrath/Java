# 216. Can we define private methods in an interface?

**Yes, since Java 9.** Both `private` and `private static` methods are allowed. Their purpose is to **share code between `default` / `static` methods** without exposing that helper as part of the public contract.

```java
public interface Logger {
    default void info(String msg)  { log("INFO", msg); }
    default void error(String msg) { log("ERROR", msg); }

    private void log(String level, String msg) {          // helper, hidden from implementers
        System.out.println(format(level, msg));
    }

    private static String format(String level, String msg) {
        return "[" + level + "] " + msg;
    }
}
```

Rules:
- A private method **must have a body**. It can't be `abstract` or `default`.
- It is **not inherited** and can't be called or overridden by implementing classes.
- A `private` (instance) method can be called from default and private methods. A `private static` method can also be called from `static` methods.
- `protected` methods are still **not** allowed in interfaces.

---

[← Back to Easy index](README.md)
