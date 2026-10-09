# 180. How does multi-threading take place on a computer with a single CPU?

On a single CPU, Java achieves multithreading using time-sharing (time-slicing), where the CPU rapidly switches between threads to create the illusion of parallel execution. The operating system manages thread scheduling and allocates small time slices to each thread.

To prevent issues like race conditions, Java provides synchronization and locking mechanisms. While this approach enables efficient multitasking, it only simulates parallelism on a single CPU, so performance gains are limited compared to multi-core systems.

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#180-how-does-multithreading-take-place-on-a-computer-with-a-single-cpu)
