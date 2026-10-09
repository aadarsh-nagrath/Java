# 50. Why is StringBuffer called mutable?

[StringBuffer](https://www.geeksforgeeks.org/java/stringbuffer-class-in-java/) is called mutable because its content can be changed after the object is created without creating a new object.

- Unlike String, which creates a new object for every modification, StringBuffer modifies the same existing object.
- This makes it memory efficient for frequent string updates.
- It provides methods like append(), insert(), and delete() to modify data directly.
- StringBuffer is mutable because its value can be changed in the same memory location.

```java
// Java Program to demonstrate use of stringbuffer
public class StringBufferExample {
    public static void main(String[] args)
    {
        StringBuffer s = new StringBuffer();
        s.append("Geeks");
        s.append("for");
        s.append("Geeks");
        String message = s.toString();
        System.out.println(message);
    }
}
```

**Output**

```text
GeeksforGeeks
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#50-why-is-stringbuffer-called-mutable)
