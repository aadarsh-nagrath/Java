# 145. Differentiate between ArrayList and Vector in Java.

| ArrayList | Vector |
| --- | --- |
| ArrayList is implemented as a resizable array. | Vector is implemented as a synchronized, resizable array. |
| ArrayList is not synchronized. | The vector is synchronized. |
| ArrayLists are Faster for non-concurrent operations. | Vector is Slower for non-concurrent operations due to added overhead of synchronization. |
| ArrayLists were Introduced in Java 1.2. | Vector was Introduced in JDK 1.0. |
| Recommended for use in a single-threaded environment. | Vectors are Recommended for use in a multi-threaded environment. |
| The default initial capacity of ArrayLists is 10. | In Vectors, the default initial capacity is 10 but the default increment is twice the size. |
| ArrayList performance is high. | Vector performance is low. |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#145-differentiate-between-arraylist-and-vector-in-java)
