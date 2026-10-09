# 174. Differentiate between process and thread?

A process and a thread are both units of execution in a computer system, but they are different in several ways:

| Process | Thread |
| --- | --- |
| A process is a program in execution. | A thread is a single sequence of instructions within a process. |
| The process takes more time to terminate. | The thread takes less time to terminate. |
| The process takes more time for context switching. | The thread takes less time for context switching. |
| The process is less efficient in terms of communication. | Thread is more efficient in terms of communication. |
| The process is isolated. | Threads share memory. |
| The process has its own Process Control Block, Stack, and Address Space. | Thread has Parents’ PCB, its own Thread Control Block, and Stack and common Address space. |
| The process does not share data with each other. | Threads share data with each other. |

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#174-differentiate-between-process-and-thread)
