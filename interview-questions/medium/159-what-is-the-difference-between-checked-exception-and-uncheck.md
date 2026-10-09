# 159. What is the difference between Checked Exception and Unchecked Exception?

Checked and Unchecked Exceptions differ in how the compiler handles them. Checked exceptions are verified at compile time, while unchecked exceptions occur at runtime.

- Checked Exceptions must be handled using try-catch or declared with throws (e.g., IOException, SQLException).
- Unchecked Exceptions are subclasses of RuntimeException and are not checked by the compiler (e.g., NullPointerException, ArithmeticException).
- Checked exceptions usually represent external issues, whereas unchecked exceptions often result from programming errors.

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#159-what-is-the-difference-between-checked-exception-and-unchecked-exception)
