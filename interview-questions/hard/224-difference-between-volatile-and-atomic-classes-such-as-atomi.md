# 224. Difference between `volatile` and Atomic classes such as `AtomicInteger` and `AtomicFloat`.

| | `volatile` | `AtomicInteger`, `AtomicLong`, `AtomicReference`, … |
| --- | --- | --- |
| Visibility | ✅ | ✅ (internally holds a `volatile` value) |
| Atomic compound ops (`++`, check-then-act) | ❌ | ✅ `incrementAndGet()`, `compareAndSet()`, `updateAndGet()` |
| Mechanism | Memory barrier | **CAS** (compare-and-swap) CPU instruction, lock-free |
| Use case | Flags, status published by one writer | Counters, sequence generators, lock-free updates |

```java
volatile int v = 0;
v++;                                  // ❌ race: two threads can both read 5 and write 6

AtomicInteger a = new AtomicInteger();
a.incrementAndGet();                  // ✅ atomic
a.updateAndGet(x -> Math.max(x, 10));
a.compareAndSet(10, 20);              // set to 20 only if currently 10
```

**Trap in the question: there is no `AtomicFloat` (or `AtomicDouble`) in the JDK.** The atomic classes are `AtomicBoolean`, `AtomicInteger`, `AtomicLong`, `AtomicReference`, their `*Array` variants, and `LongAdder` / `DoubleAdder` / `LongAccumulator` / `DoubleAccumulator`. For floating point:
- `DoubleAdder` / `DoubleAccumulator` for sums and statistics under contention.
- Store bits in an `AtomicInteger` with `Float.floatToIntBits()` / `Float.intBitsToFloat()` (or `AtomicLong` + `Double.doubleToLongBits()`).
- Guava's `AtomicDouble`.

**Performance note:** under heavy contention, many threads retrying CAS on one `AtomicLong` is slow. `LongAdder` spreads the updates across cells and is much faster for counters that are written often and read rarely.

---

[← Back to Hard index](README.md)
