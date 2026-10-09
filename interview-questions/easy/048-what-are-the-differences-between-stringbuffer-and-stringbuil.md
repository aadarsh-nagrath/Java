# 48. What are the differences between StringBuffer and StringBuilder?

| StringBuffer | StringBuilder |
| --- | --- |
| [StringBuffer](https://www.geeksforgeeks.org/java/stringbuffer-class-in-java/) provides functionality to work with the strings. | [StringBuilder](https://www.geeksforgeeks.org/java/stringbuilder-class-in-java-with-examples/) is a class used to build a mutable string. |
| It is thread-safe (two threads can't call the methods of StringBuffer simultaneously) | It is not thread-safe (two threads can call the methods concurrently) |
| Comparatively slow as it is synchronized. | Being non-synchronized, implementation is faster |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#48-what-are-the-differences-between-stringbuffer-and-stringbuilder)
