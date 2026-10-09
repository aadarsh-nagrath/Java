# 176. Explain the sleep() method under the Thread class.

The sleep() method in Java is used to pause the execution of the currently running thread for a specified period of time. During this time, the thread remains in a timed waiting state but does not release any locks or monitors it holds. After the specified sleep duration, the thread becomes runnable again.

**Syntax:**

> public static void sleep(long milliseconds) throws InterruptedException;
> public static void sleep(long milliseconds, int nanoseconds) throws InterruptedException;

**Example:**

```java
class DemoSleep extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread running: " + i);
            try {
                Thread.sleep(1000); // pauses for 1 second
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public static void main(String[] args) {
        DemoSleep t = new DemoSleep();
        t.start();
    }
}
```

**Output:**

```
Thread running: 1
Thread running: 2
Thread running: 3
Thread running: 4
Thread running: 5
```

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#176-explain-suspend-method-under-the-thread-class)
