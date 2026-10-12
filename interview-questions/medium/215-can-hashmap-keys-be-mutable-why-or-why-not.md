# 215. Can HashMap keys be mutable? Why or why not?

**Technically yes, but you shouldn't mutate them.** Java won't stop you using a mutable object as a key. If you **change a field used in `hashCode()`/`equals()` after insertion**, the entry gets lost.

Why: `put` stores the entry in the bucket for `hash(key)` at insertion time. After mutation, `get` computes a **different hash**, looks in a different bucket and returns `null`. Even `containsKey` fails, and the entry leaks until the map is cleared.

```java
class Emp { String name; /* equals & hashCode use name */ }

Map<Emp, Integer> map = new HashMap<>();
Emp e = new Emp("A");
map.put(e, 1);
e.name = "B";                    // mutate key
map.get(e);                      // null  ❌ (looks in the bucket for "B")
map.get(new Emp("A"));           // null  ❌ (right bucket, but equals fails)
map.size();                      // 1, a "zombie" entry
```

**Rules for good keys**
- Make keys **immutable**: `String`, `Integer`, enums, **records**, or classes with `final` fields.
- If you must use a mutable class, base `equals`/`hashCode` only on fields that never change (e.g. an ID).
- Same rule for `HashSet` elements and `TreeMap` keys (where the `compareTo` fields must not change).

See also: [learn/03, HashMap internals & equals/hashCode](../../learn/03-collections-hashmap.md) · [138. Can you use any class as a Map key?](../easy/138-can-you-use-any-class-as-a-map-key.md)

---

[← Back to Medium index](README.md)
