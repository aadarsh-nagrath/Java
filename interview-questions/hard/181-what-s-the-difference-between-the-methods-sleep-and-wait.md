# 181. What's the difference between the methods sleep() and wait()?

| Sleep() | Wait() |
| --- | --- |
| The sleep() method belongs to the thread class. | Wait() method belongs to the object class. |
| Sleep does not release the lock that the current thread holds. | wait() release the lock which allows other threads to acquire it. |
| This method is a static method. | This method is not a static method. |
| Sleep() does not throw an InterruptedException. | InterruptedException is shown if the thread is interrupted while waiting. |
| Mainly used to delay a thread for some specific time duration. | Mainly used to pause a thread until notified by another thread. |
| Sleep() Has Two Overloaded Methods:   - sleep(long millis)millis: milliseconds - sleep(long millis, int nanos) nanos: Nanoseconds | Wait() Has Three Overloaded Methods:   - wait() - wait(long timeout) - wait(long timeout, int nanos) |

---

[← Back to Hard index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#46-whats-the-difference-between-the-methods-sleep-and-wait)
