# 164. What do you understand by Object Cloning and how do you achieve it in Java?

Object Cloning is the process of creating an exact copy of an existing object in Java. The cloned object has the same state but is a separate instance in memory.

- Object cloning is achieved using the clone() method provided by the Object class.
- The class must implement the Cloneable interface to allow cloning; otherwise, it throws CloneNotSupportedException.
- It performs a shallow copy by default unless explicitly customized for deep copy.

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#164-what-do-you-understand-by-object-cloning-and-how-do-you-achieve-it-in-java)
