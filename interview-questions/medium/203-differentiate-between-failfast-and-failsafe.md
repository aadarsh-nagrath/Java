# 203. Differentiate between Failfast and Failsafe.

| FailFast | FailSafe |
| --- | --- |
| Failsfast fails immediately when it detects concurrent modification during the time of iteration. | Failsafe continues to iterate over the original collection and also creates a copy to modify. |
| Failfast is generally used in single-threaded environments. | Failsafe is used in multithreaded environments. |
| Failfast does not allow any modification while iteration. | Failsafe allows modification during the time of iteration. |
| Failfast is fast compared to failsafe as it does not involve the copying of the collection. | Failsafe is generally slow compared to failfast. |
| FailFast throws ConcurrentModificationException if the collection is modified during iteration. | FailSafe does not throws any exception but instead, it creates a copy of the collection to iterate. |

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#203-differentiate-between-failfast-and-failsafe)
