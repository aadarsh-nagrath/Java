# 158. When is the ArrayStoreException thrown?

ArrayStoreException is thrown when an attempt is made to store the wrong type of object in an array of objects.

```java
// Java Program to implement
// ArrayStoreException
public class GFG {
    public static void main(String args[])
    {
        // Since Double class extends Number class
        // only Double type numbers
        // can be stored in this array
        Number[] a = new Double[2];
        // Trying to store an integer value
        // in this Double type array
        a[0] = new Integer(4);
    }
}
```

**Example:**

```
Exception in thread "main" java.lang.ArrayStoreException: java.lang.Integer
    at GFG.main(GFG.java:6)
```

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#158-when-is-the-arraystoreexception-thrown)
