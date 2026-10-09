# 24. What are the default values assigned to variables and instances in Java?

In Java When we haven’t initialized the instance variables then the compiler initializes them with default values. The default values for instances and variables depend on their data types. Some common types of default data types are:

- The default value for numeric types (byte, short, int, long, float, and double) is 0.
- The default value for the boolean type is false.
- The default value for object types (classes, interfaces, and arrays) is null.
- The null character, "u0000, " is the default value for the char type.

**Example:**

```java
// Java Program to demonstrate use of default values
import java.io.*;
class GFG {
    // static values
    static byte b;
    static int i;
    static long l;
    static short s;
    static boolean bool;
    static char c;
    static String str;
    static Object object;
    static float f;
    static double d;
    static int[] Arr;
    public static void main(String[] args)
    {
        // byte value
        System.out.println("byte value" + b);
        // short value
        System.out.println("short value" + s);
        // int value
        System.out.println("int value" + i);
        // long value
        System.out.println("long value" + l);
        System.out.println("boolean value" + bool);
        System.out.println("char value" + c);
        System.out.println("float value" + f);
        System.out.println("double value" + d);
        System.out.println("string value" + str);
        System.out.println("object value" + object);
        System.out.println("Array value" + Arr);
    }
}
```

**Output:**

> byte value0
> short value0
> int value0
> long value0
> boolean valuefalse
> char value
> float value0.0
> double value0.0
> string valuenull
> object valuenull
> Array valuenull

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#24-what-are-the-default-values-assigned-to-variables-and-instances-in-java)
