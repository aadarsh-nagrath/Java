# 100. Can the constructor be inherited?

No, constructors cannot be inherited in Java.

- Constructors are not members of a class; they are special blocks used to initialize objects, so they are not inherited by subclasses.
- However, a subclass can call the parent class constructor using the super() keyword.
- If a subclass does not explicitly call a constructor, Java automatically calls the default constructor of the parent class.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#100-can-the-constructor-be-inherited)
