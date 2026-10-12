# 223. What is the `volatile` keyword?

`volatile` is a field modifier that guarantees **visibility** and **ordering** of reads and writes across threads.

- **Visibility:** each write goes straight to main memory, and each read gets the latest value. Without it, a thread can keep reading a stale value cached in a CPU register or cache, potentially forever.
- **Ordering (happens-before):** a write to a volatile variable *happens-before* every subsequent read of it. All writes made before the volatile write are visible to a thread that reads the volatile variable. It also stops the compiler or CPU from **reordering** instructions around it.
- **NOT atomicity:** `count++` (read, add, write) is still a race condition on a `volatile int`.

**Typical use: a stop flag**
```java
private volatile boolean running = true;

void run()  { while (running) { doWork(); } }   // without volatile this loop may never see the update
void stop() { running = false; }
```

**Double-checked locking singleton.** `volatile` prevents another thread from seeing a half-constructed object:
```java
private static volatile Singleton instance;

static Singleton get() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) instance = new Singleton();
        }
    }
    return instance;
}
```

**Use volatile when:** one thread writes and others read, or writes don't depend on the current value. Otherwise use `synchronized` or `Atomic*` classes. Also, `volatile` on an array/object reference makes only the **reference** volatile, not its elements (see [59](../easy/059-is-it-possible-to-make-an-array-volatile.md)).

---

[← Back to Medium index](README.md)
