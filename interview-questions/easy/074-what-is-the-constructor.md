# 74. What is the constructor?

[A constructor](https://www.geeksforgeeks.org/java/constructors-in-java/) is a special member of a class that is automatically called when an object is created. Its primary purpose is to initialize the object's data members.

- A constructor has the same name as the class and does not have a return type, not even void.
- It is invoked automatically when an object is created using the [new keyword.](https://www.geeksforgeeks.org/java/new-operator-java/)
- Constructors help initialize objects with default or user-defined values.

**Example:**

```java
class Student {
    String name;

    Student() { // Constructor
        name = "John";
    }
}
```

In this example, the constructor initializes the name variable when a Student object is created.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#74-what-is-the-constructor)
