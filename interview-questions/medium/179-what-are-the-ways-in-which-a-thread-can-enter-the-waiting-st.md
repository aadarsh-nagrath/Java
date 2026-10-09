# 179. What are the ways in which a thread can enter the waiting state?

Thread is a lightweight process that runs concurrently with the other thread inside a single process. Each thread can execute a different task and share the resources within a single process. Thread in Java can enter the waiting state in many different ways:

- **Sleep() method Call: The sleep** () method is used to pause the execution of the thread for a specific amount of time. While the thread is paused it goes into the waiting state.
- **Wait() method:** This method is used to wait a thread until the other thread signals it to wake up. Thread goes into the waiting state until it receives a notification from another thread.
- **Join() method:** Join() method can be used to wait for thread to finish the execution. Calling thread goes into the waiting state until the target thread is completed.
- **Waiting for I/O operations:** If the thread is waiting for Input/Output operation to complete, it goes into the waiting state until the operation is finished.
- **Synchronization Issues:** If there are any synchronization issues in a multi-threaded application, threads may go into the waiting state until the synchronization issues are resolved.

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#179-what-are-the-ways-in-which-a-thread-can-enter-the-waiting-state)
