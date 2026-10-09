# 114. What is Abstract class?

An [Abstract Class](https://www.geeksforgeeks.org/java/abstract-classes-in-java/) is a class that is declared using the abstract keyword and cannot be instantiated (objects cannot be created directly from it). It is used to provide a common base class and can contain both abstract and concrete methods.

- May contain abstract methods (without implementation) and concrete methods (with implementation).
- Cannot be instantiated directly.
- Used to achieve abstraction and promote code reuse through inheritance.

```java
// Java Program to implement
// abstract method
import java.io.*;
// Abstract class
abstract class Fruits {
    abstract void run();
}
// Driver Class
class Apple extends Fruits {
    void run()
    {
        System.out.println("Abstract class example");
    }
    // main method
    public static void main(String args[])
    {
        Fruits obj = new Apple();
        obj.run();
    }
}
```

**Output**

```text
Abstract class example
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#114-what-is-abstract-class)
