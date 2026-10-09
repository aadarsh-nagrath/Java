# 102. What is runtime polymorphism or dynamic method dispatch?

[Runtime Polymorphism](https://www.geeksforgeeks.org/java/dynamic-method-dispatch-runtime-polymorphism-java/) is a feature in which the method that gets executed is decided at runtime based on the actual object being referred to, not the reference type. It enables Java to call the overridden method of the child class through a parent class reference.

- Achieved through Method Overriding.
- Allows a parent class reference to refer to child class objects.
- Helps implement flexibility and extensibility in applications.

**Example:** If an Animal reference points to a Dog object, calling sound() will execute the Dog class's sound() method.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#102-what-is-runtime-polymorphism-or-dynamic-method-dispatch)
