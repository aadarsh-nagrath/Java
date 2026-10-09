# 132. What is LinkedHashSet in Java Collections Framework?

The [LinkedHashSet](https://www.geeksforgeeks.org/java/linkedhashset-in-java-with-examples/) is an ordered version of Hashset maintained by a doubly-linked List across all the elements. It is very helpful when iteration order is needed. During Iteration in LinkedHashSet, elements are returned in the same order they are inserted.

**Syntax:**

> LinkedHashSet\<E> hs = new LinkedHashSet\<E>();

```java
import java.io.*;
import java.util.*;

// Driver Class
class GFG {
    // Main Function
    public static void main(String[] args) {
        // LinkedHashSet declared
        LinkedHashSet<Integer> hs = new LinkedHashSet<Integer>();
        // Add elements in HashSet
        hs.add(1);
        hs.add(2);
        hs.add(5);
        hs.add(3);
        // Print values
        System.out.println("Values:" + hs);
    }
}
```

**Output**

```text
Values:[1, 2, 5, 3]
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#132-what-is-linkedhashset-in-java-collections-framework)
