# 41. Explain the difference between >> and >>> operators.

Both >> and >>> are bitwise shift operators in Java used to shift bits to the right. The main difference is how they handle the sign bit (most significant bit).

| >> (Signed Right Shift) | >>> (Unsigned Right Shift) |
| --- | --- |
| Preserves the sign bit. | Does not preserve the sign bit. |
| Fills vacant leftmost bits with the sign bit (0 for positive, 1 for negative). | Always fills vacant leftmost bits with 0. |
| Used when the sign of the number should be maintained. | Used when only the bit pattern matters. |
| Also known as Arithmetic Right Shift. | Also known as Logical Right Shift. |

```java
import java.io.*;

// Driver
class GFG {
    public static void main(String[] args) {
        int a = -16, b = 1;
        // Use of >>
        System.out.println(a >> b);
        a = -17;
        b = 1;
        // Use of >>>
        System.out.println(a >>> b);
    }
}
```

**Output**

```text
-8
2147483639
```

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#41-explain-the-difference-between-and-operators)
