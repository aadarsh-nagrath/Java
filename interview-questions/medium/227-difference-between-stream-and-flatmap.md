# 227. Difference between `stream` and `flatMap`.

> The question is usually asked as **`map` vs `flatMap`**. `stream()` just *creates* a stream from a collection, while `map` and `flatMap` are intermediate operations *on* a stream.

- **`stream()`**: turns a source into a `Stream<T>`: `list.stream()`.
- **`map(fn)`**: **one-to-one**. Each element becomes exactly one element: `Stream<T> → Stream<R>`.
- **`flatMap(fn)`**: **one-to-many + flatten**. Each element becomes a *stream* of 0..n elements, and all of those streams are merged into one: `Stream<T> → Stream<R>` where `fn: T → Stream<R>`.

```java
List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));

nested.stream().map(List::stream);        // Stream<Stream<Integer>>  ❌ nested
nested.stream().flatMap(List::stream);    // Stream<Integer> → 1, 2, 3, 4 ✅

// words in sentences
List<String> words = sentences.stream()
        .flatMap(s -> Arrays.stream(s.split(" ")))
        .toList();

// all order items of all customers
List<Item> items = customers.stream()
        .flatMap(c -> c.getOrders().stream())
        .flatMap(o -> o.getItems().stream())
        .toList();

// map: one value per element
List<Integer> lengths = words.stream().map(String::length).toList();
```

| | `map` | `flatMap` |
| --- | --- | --- |
| Function returns | A value `R` | A `Stream<R>` |
| Output count | Same as input | 0..n per input |
| Use | Transform | Flatten nested structures / expand |

The same idea applies to `Optional.map` vs `Optional.flatMap` (avoid `Optional<Optional<T>>`) and `CompletableFuture.thenApply` vs `thenCompose`. Java 16 added `mapMulti` as an imperative alternative to `flatMap`.

---

[← Back to Medium index](README.md)
