# 151. Explain the FailFast iterator and FailSafe iterator along with examples for each.

Fail-Fast and Fail-Safe are two types of iterators used in Java collections. They differ in how they behave when a collection is modified while it is being iterated

#### Fail-Fast Iterator

A Fail-Fast iterator throws a ConcurrentModificationException if the collection is modified after the iterator is created (except through the iterator's own remove() method).

- Throws ConcurrentModificationException on structural modification.
- Works directly on the original collection.
- Used by collections such as ArrayList, HashMap, and HashSet.

```java
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class GFG {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            // this will throw a ConcurrentModificationException
            if (entry.getKey() == 1) {
                map.remove(1);
            }
        }
    }
}
```

**Output:**

```
Exception in thread "main" java.util.ConcurrentModificationException
```

#### Fail-Safe Iterator

A Fail-Safe iterator works on a copy (snapshot) of the collection instead of the original collection. Therefore, modifications to the original collection do not affect the iteration.

- Does not throw ConcurrentModificationException.
- Iterates over a cloned copy of the collection.
- Used by collections such as CopyOnWriteArrayList and ConcurrentHashMap.

```java
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GFG {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            // this will not throw an exception
            if (entry.getKey() == 1) {
                map.remove(1);
            }
        }
    }
}
```

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#151-explain-the-failfast-iterator-and-failsafe-iterator-along-with-examples-for-each)
