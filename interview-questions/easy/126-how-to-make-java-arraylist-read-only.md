# 126. How to make Java ArrayList Read-Only?

An ArrayList can be made ready only using the method provided by Collections using the Collections.unmodifiableList() method.

**Syntax:**

> array_readonly = Collections.unmodifiableList(ArrayList);

```java
import java.util.*;

public class Main {
    public static void main(String[] argv) throws Exception {
        try {
            // creating object of ArrayList
            ArrayList<Character> temp = new ArrayList<>();
            // populate the list
            temp.add('X');
            temp.add('Y');
            temp.add('Z');
            // printing the list
            System.out.println("Initial list: " + temp);

            // getting readonly list
            // using unmodifiableList() method
            List<Character> new_array = Collections.unmodifiableList(temp);

            // printing the list
            System.out.println("ReadOnly ArrayList: " + new_array);

            // Attempting to add element to new Collection
            System.out.println("\nAttempting to add element to the ReadOnly ArrayList");
            new_array.add('A');
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception is thrown : " + e);
        }
    }
}
```

**Output**

```text
Initial list: [X, Y, Z]
ReadOnly ArrayList: [X, Y, Z]

Attempting to add element to the ReadOnly ArrayList
Exception is thrown : java.lang.UnsupportedOperationException
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#126-how-to-make-java-arraylist-readonly)
