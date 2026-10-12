# 222. Difference between `synchronized` and `ReentrantLock`. What are the trade-offs?

Both provide **mutual exclusion + memory visibility**, and both are **reentrant** (the same thread can re-acquire a lock it already holds).

| | `synchronized` | `ReentrantLock` (`java.util.concurrent.locks`) |
| --- | --- | --- |
| Syntax | Keyword on a method/block | Object with explicit `lock()` / `unlock()` |
| Release | **Automatic**, even on exception | **Manual**: must `unlock()` in `finally` |
| Try without blocking | ❌ | `tryLock()` |
| Timeout | ❌ | `tryLock(1, SECONDS)` (avoids deadlocks) |
| Interruptible wait | ❌ | `lockInterruptibly()` |
| Fairness | Never fair | `new ReentrantLock(true)` = FIFO-ish (slower) |
| Conditions | One wait-set (`wait`/`notify`) | Multiple `Condition`s (`notFull`, `notEmpty`) |
| Lock across methods | Must be in one block | Lock in one method, unlock in another |
| Introspection | ❌ | `isLocked()`, `getQueueLength()`, `isHeldByCurrentThread()` |

```java
private final ReentrantLock lock = new ReentrantLock();

void transfer() throws InterruptedException {
    if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
        try {
            // critical section
        } finally {
            lock.unlock();            // forget this → lock held forever
        }
    } else {
        // couldn't get the lock → back off / retry / fail fast
    }
}
```

**Trade-offs**
- `synchronized` is **simpler and safer** (no forgotten unlock) and the JVM optimises it well. **Default to it.**
- `ReentrantLock` gives **flexibility** (timeouts, interruptibility, fairness, multiple conditions) at the cost of verbosity and the risk of forgetting to unlock.
- Performance is roughly equal on modern JVMs. Choose by features, not speed.
- **Virtual threads (Java 21):** before Java 24, `synchronized` *pinned* a virtual thread to its carrier thread during blocking I/O, so `ReentrantLock` was recommended. JEP 491 (Java 24) removed that limitation.
- For read-heavy data, consider `ReentrantReadWriteLock` or `StampedLock`.

---

[← Back to Hard index](README.md)
