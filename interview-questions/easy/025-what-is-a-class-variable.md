# 25. What is a Class Variable?

A class variable (also known as a static variable) is a variable that is declared inside a class using the static keyword. It belongs to the class itself rather than to individual objects and is shared among all instances of the class.

- Declared using the static keyword inside a class but outside methods, constructors, and blocks.
- Only one copy of the variable exists, regardless of how many objects are created.
- Can be accessed using the class name without creating an object.

```java
class GFG {
    public static int ctr = 0;
    public GFG() { ctr++; }
    public static void main(String[] args)
    {
        GFG obj1 = new GFG();
        GFG obj2 = new GFG();
        GFG obj3 = new GFG();
        System.out.println("Number of objects created are "
                           + GFG.ctr);
    }
}
```

**Output**

```text
Number of objects created are 3
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#25-what-is-a-class-variable)
