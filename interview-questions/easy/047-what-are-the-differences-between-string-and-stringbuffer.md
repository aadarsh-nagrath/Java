# 47. What are the differences between String and StringBuffer?

A String and StringBuffer in Java are used to handle text data, but they differ in how they store and modify the data.

| Feature | String | StringBuffer |
| --- | --- | --- |
| Mutability | Immutable (cannot be changed after creation) | Mutable (can be changed after creation) |
| Performance | Slower for frequent modifications | Faster for frequent modifications |
| Memory Usage | Creates new object on every change | Modifies the same object |
| Thread Safety | Thread-safe due to immutability | Thread-safe (synchronized methods) |
| Usage | Best for fixed or constant data | Best for dynamic or frequently changing data |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#47-what-are-the-differences-between-string-and-stringbuffer)
