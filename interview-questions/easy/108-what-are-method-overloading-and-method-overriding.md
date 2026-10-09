# 108. What are method overloading and method overriding?

**Method Overloading:** It is also known as [Compile Time Polymorphism](https://www.geeksforgeeks.org/java/compile-time-polymorphism-in-java/). In method overloading two or more methods are shared in the same class with a different signature.

```java
// Java Program to demonstrate use of Method Overloading
import java.io.*;
class GFG {
    static int multiply(int a, int b) { return a * b; }
    static int multiply(int a, int b, int c)
    {
        return a * b * c;
    }
    static int multiply(int a, int b, int c, int d)
    {
        return a * b * c * d;
    }
    public static void main(String[] args)
    {
        System.out.println("multiply() with 2 parameters");
        System.out.println(multiply(4, 5));
        System.out.println("multiply() with 3 parameters");
        System.out.println(multiply(2, 3, 4));
        System.out.println("multiply() with 4 parameters");
        System.out.println(multiply(2, 3, 4, 1));
    }
}
```

**Output**

```text
multiply() with 2 parameters
20
multiply() with 3 parameters
24
multiply() with 4 parameters
24
```

**Method Overriding:** Method Overriding occurs when a subclass can provide the implementation of a method which is already defined in the parent class or superclass. The return type, name and arguments must be similar to the methods in superclass.

```java
// Java Program to demonstrate use of Method Overriding
import java.io.*;
class Vehicle {
    void drive()
    {
        System.out.println("drive() method of base class");
        System.out.println("driving the Car.");
    }
}
class Car extends Vehicle {
    void drive()
    {
        System.out.println(
            "drive() method of derived class");
        System.out.println("Car is driving.");
    }
}
class GFG {
    public static void main(String[] args)
    {
        Car c1 = new Car();
        Vehicle v1 = new Vehicle();
        c1.drive();
        v1.drive();
        Vehicle vehicle = new Car();
        // drive() method of Vehicle class is overridden by
        // Car class drive()
        vehicle.drive();
    }
}
```

**Output**

```text
drive() method of derived class
Car is driving.
drive() method of base class
driving the Car.
drive() method of derived class
Car is driving.
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#108-what-are-method-overloading-and-method-overriding)
