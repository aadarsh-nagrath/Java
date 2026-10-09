# 81. What is an Interface?

An interface in Java is a blueprint of a class that defines a set of abstract methods that implementing classes must provide. It is used to achieve abstraction and support multiple inheritance.

- An interface contains method declarations (and can also contain default, static, and private methods in modern Java).
- A class implements an interface using the implements keyword and provides implementations for its methods.
- Interfaces help achieve abstraction, loose coupling, and code reusability.

```java
import java.io.*;
interface Shape {
    double getArea();
    double getPerimeter();
}
class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
    public double getPerimeter()
    {
        return 2 * Math.PI * radius;
    }
}
class GFG {
    public static void main(String[] args)
    {
        Circle circle = new Circle(5.0);
        System.out.println("Area of circle is "
                           + circle.getArea());
        System.out.println("Perimeter of circle is"
                           + circle.getPerimeter());
    }
}
```

**Output**

```text
Area of circle is 78.53981633974483
Perimeter of circle is31.41592653589793
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#81-what-is-an-interface)
