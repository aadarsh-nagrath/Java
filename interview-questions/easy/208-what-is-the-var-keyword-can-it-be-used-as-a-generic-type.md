# 208. What is the `var` keyword? Can it be used as a generic type?

`var` (Java 10) gives **local variable type inference**: the compiler works out the static type from the initializer. It is still statically typed. The type is fixed at compile time, and `var` is not like JavaScript's `var`.

```java
var list = new ArrayList<String>();   // ArrayList<String>
var map  = new HashMap<String, List<Integer>>();
for (var e : map.entrySet()) { ... }
```

Technically `var` is a **reserved type name**, not a keyword, so `int var = 5;` still compiles.

**Where it is allowed**
- Local variables *with an initializer*
- `for` / enhanced-`for` loop variables, try-with-resources
- Lambda parameters (Java 11): `(var x, var y) -> x + y` (handy for adding annotations like `@NonNull var x`)

**Where it is NOT allowed**
- Fields, method parameters, return types
- Without an initializer (`var x;`) or with `null` (`var x = null;`)
- Array initializer: `var a = {1, 2};`
- **As a generic type argument**: `List<var> list` ❌ compile error

**Can it be used as a generic type?** No. `var` can only stand for the type of a whole local variable. It cannot appear inside `<>`. But it *can infer* a generic type: `var list = new ArrayList<String>()` infers `ArrayList<String>`. Watch out for the diamond: `var list = new ArrayList<>();` infers `ArrayList<Object>`.

---

[← Back to Easy index](README.md)
