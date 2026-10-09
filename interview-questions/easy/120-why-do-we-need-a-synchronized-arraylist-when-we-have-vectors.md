# 120. Why do we need a synchronized ArrayList when we have Vectors (which are synchronized) in Java?

ArrayList is in need even when we have Vectors because of certain reasons:

- ArrayList is faster than Vectors.
- ArrayList supports multithreading whereas Vectors only supports single-thread use.
- ArrayList is safer to use, as Vectors supports single threads and individual operations are less safe and take longer to synchronize.
- Vectors are considered outdated in Java because of their synchronized nature.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#120-why-do-we-need-a-synchronized-arraylist-when-we-have-vectors-which-are-synchronized-in-java)
