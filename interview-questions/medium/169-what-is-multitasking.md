# 169. What is multitasking?

Multitasking is the ability of an operating system to execute multiple tasks or processes simultaneously to improve CPU utilization and efficiency.

- In Java, multitasking allows multiple programs or processes to run at the same time.
- It is achieved using process-based multitasking (multiple programs) and thread-based multitasking (multiple threads within a program).
- Thread-based multitasking is lighter and more efficient compared to process-based multitasking.

```java
// Java program for multitasking
import java.io.*;
public class MyThread extends Thread {
    public void run()
    {
        // Code to be executed in this thread
        for (int i = 0; i < 10; i++) {
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + ": " + i);
        }
    }
}
public class GFG {
    public static void main(String[] args)
    {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        // Start the threads
        thread1.start();
        thread2.start();
    }
}
```

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#169-what-is-multitasking)
