# 95. Although inheritance is a popular OOPs concept, it is less advantageous than composition. Explain.

Inheritance is useful for achieving code reuse through an IS-A relationship, but in many real-world scenarios, composition (HAS-A relationship) is more flexible and safer.

- Inheritance creates a tight coupling between parent and child classes, so changes in the parent class can unintentionally affect all subclasses.
- Composition provides better flexibility, as objects are built by combining other objects instead of depending on a rigid class hierarchy.
- With composition, behavior can be changed at runtime by replacing components, whereas inheritance is fixed at compile time.

**Example idea:** Instead of Car extends Engine, it is better to design Car has an Engine, because an engine can be replaced without changing the car structure.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#95-although-inheritance-is-a-popular-oops-concept-it-is-less-advantageous-than-composition-explain)
