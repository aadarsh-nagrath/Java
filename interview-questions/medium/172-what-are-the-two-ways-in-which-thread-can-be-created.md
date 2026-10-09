# 172. What are the two ways in which Thread can be created?

Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of the CPU. In general, threads are small, lightweight processes with separate paths of execution. These threads use shared memory, but they act independently, thus if any one thread fails it does not affect the other threads. There are two ways to create a thread:

- By extending the Thread class
- By implementing a Runnable interface.

#### By extending the Thread class

We create a class that extends the **java.lang.Thread class**. This class overrides the run() method available in the Thread class. A thread begins its life inside run() method.

**Syntax:**

> public class MyThread extends Thread {
> public void run() {
>  // thread code goes here
>  }
> }

#### By implementing the Runnable interface

We create a new class that implements **java.lang.Runnable** interface and override run() method. Then we instantiate a Thread object and call the start() method on this object.

**Syntax:**

> public class MyRunnable implements Runnable {
> public void run() {
>  // thread code goes here
>  }
> }

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#172-what-are-the-two-ways-in-which-thread-can-be-created)
