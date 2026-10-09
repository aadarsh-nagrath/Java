# 121. Why can’t we create a generic array?

Java does not allow the direct creation of generic arrays because of type erasure. Generic type information is removed at runtime, while arrays perform type checking at runtime, which can lead to type-safety issues.

- Generics are checked at compile time, whereas arrays are checked at runtime.
- Due to type erasure, the actual generic type is not available at runtime.
- Allowing generic arrays could result in unexpected runtime errors and violate type safety.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#121-why-cant-we-create-a-generic-array)
