# 94. Is there any limitation to using Inheritance?

Yes, inheritance has some limitations in Java even though it is a powerful OOP feature.

- It can lead to tight coupling, meaning changes in the parent class may affect all child classes.
- It may increase complexity when class hierarchies become deep and difficult to manage.
- Java does not support multiple inheritance with classes, which limits flexibility.
- Overusing inheritance can reduce code flexibility, where composition might be a better choice.

**Example issue:** If a superclass changes its implementation, all subclasses inheriting it may unintentionally get affected.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#94-is-there-any-limitation-to-using-inheritance)
