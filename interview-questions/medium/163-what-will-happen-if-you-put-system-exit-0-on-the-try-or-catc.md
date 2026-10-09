# 163. What will happen if you put System.exit(0) on the try or catch block? Will finally block execute?

System.exit(int) has the capability to throw SecurityException. So, if in case of security, the exception is thrown then finally block will be executed otherwise JVM will be closed while calling System. exit(0) because of which finally block will not be executed.

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#163-what-will-happen-if-you-put-systemexit0-on-the-try-or-catch-block-will-finally-block-execute)
