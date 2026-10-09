# 186. What is a memory leak, and how does it affect garbage collection?

A memory leak in Java occurs when objects are no longer needed but still have active references, preventing the garbage collector from reclaiming memory. This often happens due to unclosed resources, unnecessary object creation, or retaining references longer than required.

As a result, unused objects accumulate in memory, negatively affecting garbage collection. This can lead to degraded performance or even application failure, making proper resource and reference management essential.

**Example:**

```java
// Java Program to demonstrate memory leaks
import java.io.*;
import java.util.Vector;
class GFG {
    public static void main(String[] args)
    {
        Vector a = new Vector(21312312);
        Vector b = new Vector(2147412344);
        Vector c = new Vector(219944);
        System.out.println("Memory Leak in Java");
    }
}
```

**Output:**

```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    at java.base/java.util.Vector.<init>(Vector.java:142)
    at java.base/java.util.Vector.<init>(Vector.java:155)
    at GFG.main(GFG.java:9)
```

---

[← Back to Hard index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#186-what-is-a-memory-leak-and-how-does-it-affect-garbage-collection)
