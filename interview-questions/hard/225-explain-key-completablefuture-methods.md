# 225. Explain key `CompletableFuture` methods.

`CompletableFuture<T>` (Java 8) is a `Future` you can **compose**: chain async steps without blocking, combine results and handle errors. By default async steps run in `ForkJoinPool.commonPool()`. **Pass your own `Executor` for I/O work.**

| Category | Methods | Meaning |
| --- | --- | --- |
| **Start** | `supplyAsync(supplier[, executor])` | Run async, returns a value |
| | `runAsync(runnable[, executor])` | Run async, no value |
| | `completedFuture(v)`, `complete(v)`, `completeExceptionally(ex)` | Create or complete manually |
| **Transform** (like `map`) | `thenApply(fn)` | `T → U` |
| **Consume** | `thenAccept(consumer)`, `thenRun(runnable)` | Side effect, no result |
| **Chain** (like `flatMap`) | `thenCompose(fn)` | `T → CompletableFuture<U>`. Avoids `CF<CF<U>>` |
| **Combine two** | `thenCombine(other, bifn)` | Both done → merge results |
| | `applyToEither`, `acceptEither` | Whichever finishes first |
| **Combine many** | `allOf(cf...)` | `CF<Void>` done when **all** complete |
| | `anyOf(cf...)` | Done when the **first** completes |
| **Errors** | `exceptionally(fn)` | Fallback value on failure |
| | `handle((res, ex) -> ...)` | Always runs, can transform result *or* error |
| | `whenComplete((res, ex) -> ...)` | Side effect (logging), passes the result through |
| **Timeouts** (9) | `orTimeout(d, unit)`, `completeOnTimeout(v, d, unit)` | Fail or default after a timeout |
| **Get result** | `join()` | Blocks; throws unchecked `CompletionException` |
| | `get()` | Blocks; throws checked `ExecutionException`, `InterruptedException` |

Every `thenX` has a `thenXAsync` variant that runs the step on another thread (optionally with your executor).

```java
ExecutorService io = Executors.newFixedThreadPool(10);

CompletableFuture<User>  userF  = CompletableFuture.supplyAsync(() -> userClient.get(id), io);
CompletableFuture<List<Order>> ordersF = CompletableFuture.supplyAsync(() -> orderClient.get(id), io);

UserProfile profile = userF
        .thenCombine(ordersF, UserProfile::new)            // parallel calls, then merge
        .orTimeout(2, TimeUnit.SECONDS)
        .exceptionally(ex -> UserProfile.empty())          // fallback
        .join();

// fan-out over a list
List<CompletableFuture<Price>> fs = ids.stream()
        .map(i -> CompletableFuture.supplyAsync(() -> priceClient.get(i), io)).toList();
List<Price> prices = CompletableFuture.allOf(fs.toArray(CompletableFuture[]::new))
        .thenApply(v -> fs.stream().map(CompletableFuture::join).toList())
        .join();
```

**Gotchas:** don't do blocking I/O on the common pool; `allOf` returns `Void`, so you collect results yourself; exceptions get wrapped in `CompletionException`; `cancel(true)` does **not** interrupt the running task.

---

[← Back to Hard index](README.md)
