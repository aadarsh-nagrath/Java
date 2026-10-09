# 167. What purpose do the keywords final, finally, and finalize fulfill?

#### final

final is a keyword is used with the variable, method, or class so that they can't be overridden.

```java
// Java Program to use final
// keyword
import java.io.*;
// Driver Class
class GFG {
    // Main function
    public static void main(String[] args)
    {
        final int x = 100;
        x = 50;
    }
}
```

**Output:**

```
./GFG.java:6: error: cannot assign a value to final variable x
          x=50;
          ^
1 error
```

#### finally

finally is a block of code used with "try-catch" in exception handling. Code written in finally block runs despite the fact exception is thrown or not.

```java
// Java Program to implement finally
import java.io.*;
// Driver class
class GFG {
    // Main function
    public static void main(String[] args)
    {
        int x = 10;
        // try block
        try {
            System.out.println("Try block");
        }
        // finally block
        finally {
            System.out.println(
                "Always runs even without exceptions");
        }
    }
}
```

**Output**

```text
Try block
Always runs even without exceptions
```

#### finalize

It is a method that is called just before deleting/destructing the objects which are eligible for Garbage collection to perform clean-up activity.

```java
/*package whatever // do not write package name here */
import java.io.*;
class GFG {
    public static void main(String[] args)
    {
        System.out.println("Main function running");
        System.gc();
    }
    // Here overriding finalize method
    public void finalize()
    {
        System.out.println("finalize method overridden");
    }
}
```

**Output**

```text
Main function running
```

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#167-what-purpose-do-the-keywords-final-finally-and-finalize-fulfill)
