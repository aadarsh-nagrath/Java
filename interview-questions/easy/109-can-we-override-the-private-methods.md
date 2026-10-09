# 109. Can we override the private methods?

No, private methods cannot be overridden in Java because they are accessible only within the class in which they are declared. Since child classes cannot access private methods, they cannot provide an overriding implementation.

- Private methods are not inherited by child classes.
- A method with the same name in the child class is treated as a new method, not an overridden one.
- Method overriding requires the method to be visible to the subclass.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#109-can-we-override-the-private-methods)
