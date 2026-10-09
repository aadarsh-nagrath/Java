# 138. Can you use any class as a Map key?

Yes, we can use any class as a Map Key if it follows certain predefined rules mentioned below:

- The class overriding the equals() method must also override the hashCode() method
- The concurrentHashMap class is thread-safe.
- The default concurrency level of ConcurrentHashMap is 16.
- Inserting null objects in ConcurrentHashMap is not possible as a key or as value.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#138-can-you-use-any-class-as-a-map-key)
