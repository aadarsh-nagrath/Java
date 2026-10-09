# 154. Difference between an Error and an Exception.

| Errors | Exceptions |
| --- | --- |
| Recovering from Errors is not possible. | Recover from exceptions by either using a try-catch block or throwing exceptions back to the caller. |
| Errors are all unchecked types in Java. | It includes both checked as well as unchecked types that occur. |
| Errors are mostly caused by the environment in which the program is running. | The program is mostly responsible for causing exceptions. |
| Errors can occur at compile time as well as run time. Compile Time: Syntax Error, Run Time: Logical Error. | All exceptions occur at runtime but checked exceptions are known to the compiler while unchecked are not. |
| They are defined in java.lang.Error package. | They are defined in java.lang.Exception package |
| **Examples:** java.lang.StackOverflowError, java.lang.OutOfMemoryError | **Examples:** Checked Exceptions: SQLException, IOException Unchecked Exceptions: ArrayIndexOutOfBoundException, NullPointerException, ArithmeticException. |

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#154-difference-between-an-error-and-an-exception)
