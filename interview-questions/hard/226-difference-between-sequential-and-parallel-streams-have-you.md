# 226. Difference between sequential and parallel streams. Have you used parallel streams in a production project?

| | Sequential (`stream()`) | Parallel (`parallelStream()` / `.parallel()`) |
| --- | --- | --- |
| Threads | Caller thread only | Caller + **`ForkJoinPool.commonPool()`** (size = cores − 1) |
| How | One element after another | Source **split** (`Spliterator`) into chunks, processed concurrently, then results combined |
| Order | Encounter order kept | `forEach` order is random (`forEachOrdered` keeps order but costs) |
| Overhead | None | Splitting, thread coordination, merging |
| Thread safety needed | No | Yes. Lambdas must be stateless with no shared mutable state |

**When parallel helps:** large data sets (roughly 10k+ elements) of **CPU-bound**, independent work, with an easily splittable source (`ArrayList`, arrays, `IntStream.range`) and cheap merging (`sum`, `count`, `toList`).

**When it hurts:**
- Small collections. The overhead outweighs the gain.
- **Blocking I/O** (DB or HTTP calls). It ties up the *shared* common pool, which slows down every other parallel stream and `CompletableFuture` in the JVM.
- `LinkedList`, `Stream.iterate`, I/O-backed sources (they split poorly).
- Order-dependent ops: `limit`, `findFirst`, `sorted`.
- Shared mutable state: `list.parallelStream().forEach(result::add)` on an `ArrayList` is a **bug** (lost elements, exceptions). Use `collect(...)` instead.
- In web servers, request threads are already parallel, so adding more parallelism rarely helps throughput.

```java
long primes = LongStream.rangeClosed(1, 5_000_000).parallel().filter(Util::isPrime).count();   // good fit
```

### "Have you used it in production?" — a sample honest answer
> "Rarely, and only after measuring. We used it in a nightly batch job that computed risk scores over ~2 million in-memory records. It was pure CPU work on an `ArrayList`, and switching to `parallelStream()` cut the run from about 40 s to 12 s on an 8-core box, which we checked with a benchmark. For anything involving DB or REST calls we deliberately *didn't*, because that blocks the common ForkJoinPool. There we used `CompletableFuture` with a dedicated, bounded `ExecutorService` instead."

The key signals interviewers look for: **measure first, CPU-bound only, avoid the common pool for I/O, know about thread safety.** (If you need a custom pool: `new ForkJoinPool(4).submit(() -> list.parallelStream()...).get()`.)

---

[← Back to Hard index](README.md)
