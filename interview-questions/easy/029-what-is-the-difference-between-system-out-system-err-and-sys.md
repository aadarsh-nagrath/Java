# 29. What is the difference between System.out, System.err, and System.in?

**System.out -** It is a PrintStream that is used for writing characters or can be said it can output the data we want to write on the Command Line Interface console/terminal.

```java
import java.io.*;
// Driver Class
class GFG {
    // Main Function
    public static void main(String[] args)
    {
        // Use of System.out
        System.out.println("");
    }
}
```

**System.err -** It is used to display error messages.

```java
import java.io.*;
// Driver Class
class GFG {
    // Main function
    public static void main(String[] args)
    {
        // Printing error
        System.err.println(
            "This is how we throw error with System.err");
    }
}
```

**Output:**

> This is how we throw error with System.err

Although, System.err have many similarities both of them have quite a lot of difference also, let us check them.

| **System.out** | **System.err** |
| --- | --- |
| It will print to the standard out of the system. | It will print to the standard error. |
| It is mostly used to display results on the console. | It is mostly used to output error texts. |
| It gives output on the console with the default(black) color. | It also gives output on the console but most of the IDEs give it a red color to differentiate. |

**System.in -** It is an InputStream used to read input from the terminal Window. We can't use the System.in directly so we use Scanner class for taking input with the system.in.

```java
import java.util.*;
// Driver Class
class Main {
    // Main Function
    public static void main(String[] args)
    {
        // Scanner class with System.in
        Scanner sc = new Scanner(System.in);
        // Taking input from the user
        int x = sc.nextInt();
        int y = sc.nextInt();
        // Printing the output
        System.out.printf("Addition: %d", x + y);
    }
}
```

**Output:**

> 3
> 4
> Addition: 7

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#29-what-is-the-difference-between-systemout-systemerr-and-systemin)
