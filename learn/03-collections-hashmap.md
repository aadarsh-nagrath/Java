# 03 · Collections, ArrayList vs LinkedList, HashMap Internals, `equals()`/`hashCode()`

> **Legend:** `💬 Me:` = my own question/answer · `✅` = confirmed · **➕ Added** = extra notes added later to fill gaps

---

## 1. Important Java collection hierarchy

Remember this:

```text
             Collection
                 |
      ----------------------
      |                    |
     List                 Set

                 Map (separate)
```

**`Map` is not a `Collection`.**

> **➕ Added — the fuller picture (with the classes you'll actually use)**
>
> ```text
>                         Iterable
>                            |
>                        Collection
>          ┌─────────────────┼──────────────────┐
>        List               Set               Queue
>     ┌────┴─────┐     ┌─────┼──────┐       ┌────┴──────┐
> ArrayList LinkedList HashSet  TreeSet  PriorityQueue  Deque
>                         |                              |
>                   LinkedHashSet                    ArrayDeque
>
>                          Map  (separate tree)
>              ┌────────────┼─────────────┐
>          HashMap       TreeMap   ConcurrentHashMap
>              |
>        LinkedHashMap
> ```
>
> | Need | Use | Order |
> |---|---|---|
> | Ordered list, duplicates OK | `ArrayList` | Insertion |
> | Unique items, fastest | `HashSet` | ❌ None |
> | Unique, keep insertion order | `LinkedHashSet` | Insertion |
> | Unique, always sorted | `TreeSet` | Sorted |
> | Key → value, fastest | `HashMap` | ❌ None |
> | Key → value, insertion order | `LinkedHashMap` | Insertion |
> | Key → value, sorted keys | `TreeMap` | Sorted |
> | Stack / Queue | `ArrayDeque` | — |
> | Always get min/max first | `PriorityQueue` | Heap |
>
> 💡 `Stack` and `Vector` are old legacy classes — use `ArrayDeque` for a stack (`push`/`pop`).

---

## 2. Picking the right collection — quiz

### 2.1 Users where duplicate emails are not allowed

> 💬 **Me:** Set / Map

✅ Both are valid depending on the requirement.

If you only care about **uniqueness**:

```java
Set<String> emails = new HashSet<>();
```

```text
a@test.com
b@test.com
```

No duplicates.

But in a real backend, you usually need `email → User`. So:

```java
Map<String, User> users = new HashMap<>();
```

is better, because `"a@test.com" → User object` — you can find the user instantly.

**So backend answer: `Map`.**

### 2.2 Order history where duplicates are allowed and order matters

> 💬 **Me:** Stack ❌

Why not? A stack means **Last In First Out (LIFO)**:

```text
A
B
C

pop() → C
```

Order history usually needs:

```text
Order 1
Order 2
Order 3
```

You want to see history in **insertion order**. Use:

```java
List<Order> orders = new ArrayList<>();
```

Because:

- duplicates allowed ✅
- order preserved ✅
- index access ✅

### 2.3 Leaderboard

> 💬 **Me:** List

Depends.

Simple leaderboard: `List<Player> players;` then sort.

But if you need:

- always sorted
- fast ranking updates

you might use:

- `TreeSet`
- `PriorityQueue`

> **➕ Added — how do you tell Java *how* to sort? `Comparable` vs `Comparator`**
>
> ```java
> // 1. Comparable → the class's "natural" order (one only)
> class Player implements Comparable<Player> {
>     String name; int score;
>     @Override
>     public int compareTo(Player other) {
>         return Integer.compare(other.score, this.score);   // highest score first
>     }
> }
> Collections.sort(players);
>
> // 2. Comparator → any order you want, from outside the class (preferred, modern)
> players.sort(Comparator.comparingInt((Player p) -> p.score).reversed());
> players.sort(Comparator.comparing(p -> p.name));
>
> // TreeSet / PriorityQueue use the same idea
> TreeSet<Player> board = new TreeSet<>(Comparator.comparingInt((Player p) -> p.score).reversed());
> ```
>
> ⚠️ `TreeSet` treats two items that compare as `0` as **duplicates** — two players with the same score would collapse into one. Add a tie-breaker (e.g. `.thenComparing(p -> p.name)`).

---

## 3. ArrayList — `add()` at the end

One more subtle point. For `ArrayList`:

```java
list.add(value);
```

at the end — usually:

```text
[1][2][3][4][ ]
              ↑
          add here
```

No shifting → **O(1)**

But sometimes:

```text
[1][2][3][4][5]
```

array full. Java creates a bigger array:

```text
old array → new bigger array
copy everything
add new item
```

That single operation is **O(n)**. But averaged over many adds: **amortized O(1)**.

> **➕ Added:** `ArrayList` grows by ~**1.5×** each time (default starting capacity 10). If you know the size up front, pre-size it: `new ArrayList<>(10_000)` to avoid repeated copying.

### Why O(n/2) is still O(n)

> 💬 **Me:** Adding in middle should be n/2 because only half the elements shift.

Good observation. In Big-O, **we drop constants**.

```text
n = 100
Middle insertion: 50 elements shift
Technically: O(n/2)
```

But Big-O ignores the constant: **O(n/2) = O(n)**

Why? Because as n grows:

```text
n = 1000      → 500 moves
n = 1,000,000 → 500,000 moves
```

The growth pattern is still linear. `O(2n)`, `O(n)`, `O(n/2)` — all are called **O(n)** because they grow linearly.

---

## 4. Insert in middle — ArrayList vs LinkedList

### ArrayList

```text
[10][20][30][40]
```

Insert 25 at index 2. ArrayList must shift:

```text
Before: [10][20][30][40]

Move:   30 → right
        40 → right

After:  [10][20][25][30][40]
```

It has to move elements. **Time: O(n)**

### LinkedList

```text
Before: 10 → 20 → 30 → 40
```

Insert 25 after 20. You create a new node `25` and change pointers:

```text
Before: 20 → 30
After:  20 → 25 → 30
```

Only pointer changes. **Time: O(1)**

### ⚠️ BUT — important interview correction

**Finding** the middle position in LinkedList is slow.

```java
list.add(50000, value);
```

LinkedList has to walk:

```text
head → node1 → node2 → ... → node50000
```

That is O(n). So the full operation is:

| Step | Cost |
|---|---|
| Find location | O(n) |
| Insert | O(1) |
| **Total** | **O(n)** |

Whereas if you **already have the node reference**: Insert = O(1).

> **➕ Added — Big-O cheat sheet**
>
> | Operation | ArrayList | LinkedList | HashMap / HashSet | TreeMap / TreeSet |
> |---|---|---|---|---|
> | `get(index)` | **O(1)** | O(n) | — | — |
> | add at end | O(1) amortized | O(1) | — | — |
> | add/remove in middle | O(n) | O(n) (find) + O(1) | — | — |
> | `contains` / search | O(n) | O(n) | **O(1)** avg | O(log n) |
> | put / get by key | — | — | **O(1)** avg | O(log n) |
>
> In practice, **`ArrayList` beats `LinkedList` almost always** (CPU cache-friendly, less memory per element). Use `ArrayList` by default.

---

## 5. HashMap — how it works internally

A `HashMap` is basically a **hash table**.

**Question:** If I have

```java
Map<String, User> users = new HashMap<>();

users.put("aadarsh@gmail.com", user);
```

why do you think `users.get("aadarsh@gmail.com");` is usually O(1)? What magic is happening internally?

> 💬 **Me:** It's definitely O(1) as they are stored in key-value pairs. Internally, however, I might assume — like we have linked list which has a value and a pointer — I think map has key, and that key node points to value node, something like that. I don't really know what data structure it might use internally, but the key var has the memory address to value definitely.

Good intuition. You're close, but one important correction.

*"key has the memory address to value"* ❌ **Not exactly.**

A HashMap does not store the key as a pointer directly to the value. **The magic is hashing.**

```java
users.put("aadarsh@gmail.com", user);
```

Internally something like this happens:

### Step 1: Hash the key

Java takes `"aadarsh@gmail.com"` and calculates a number:

```text
hashCode()
    ↓
  123456
```

### Step 2: Find a bucket

HashMap has an internal **array** (bucket array):

```text
0
1
2
3
4
5
6
7
...
```

It calculates `123456 % array_size`. Suppose `bucket = 5`. Now it stores:

```text
Bucket[5]
  Key:   "aadarsh@gmail.com"
  Value: User object
```

So lookup `users.get("aadarsh@gmail.com")` does:

```text
key
 |
hash
 |
bucket index
 |
directly jump there
 |
get value
```

That's why average: **O(1)**.

> **➕ Added:** Real Java uses a bitwise trick, `hash & (n - 1)`, instead of `%` (that's why the array size is always a power of 2) — same idea, just faster.

### Where your linked list idea comes in

Inside a bucket, **collisions** can happen:

```text
Bucket[5]

(key1,value1)
   |
(key2,value2)
   |
(key3,value3)
```

Historically this was a LinkedList. Modern Java (**Java 8+**) can convert a long collision chain into a **balanced tree**.

So mental model:

```text
HashMap

Array
 |
 |-- Bucket 0
 |-- Bucket 1
 |-- Bucket 2
       |
       └── key-value entries
```

---

## 6. If HashMap is O(1), why isn't everything stored in a HashMap?

Why do we still need `ArrayList`? (Think about ordering, indexing, memory, etc.)

**ArrayList gives you:**

```java
list.get(0);
list.get(1);
list.get(2);
```

It is **ordered and index-based**.

```text
Orders:
0 → Order A
1 → Order B
2 → Order C
```

You care about **sequence**.

**HashMap gives you:**

```text
userId → User
```

You care about **lookup**.

```java
users.get(12345);
```

You don't care where it is stored internally.

### Other differences

| | ArrayList | HashMap |
|---|:---:|:---:|
| Access by index | ✅ | ❌ |
| Key lookup | ❌ | ✅ |
| Maintains order | ✅ | depends on implementation |
| Allows duplicates | ✅ | keys ❌ |
| Memory | Less | More |

---

## 7. Collisions

This:

```text
hashCode()
   ↓
 123456

123456 % array_size
   ↓
 bucket 5
```

is just a way to decide **where to put it**. But **different keys can produce the same bucket**.

Imagine array size = 10:

```text
"aadarsh"  hashCode = 12345   12345 % 10 = 5
"john"     hashCode = 67895   67895 % 10 = 5
```

Both go to `Bucket[5]`. **That is a collision.**

So Java stores both:

```text
Bucket[5]

("aadarsh", User1)
        |
("john", User2)
```

Then when you search `map.get("john")`, Java:

1. Calculates hash of `"john"`
2. Goes to bucket 5
3. Compares keys using `equals()`
4. Finds `"john"`

### One important interview point

HashMap speed depends on two things: **`hashCode()` + `equals()`**

- `hashCode()` **finds the bucket**.
- `equals()` **confirms the exact key**.

---

## 8. Collision resolution — step by step

```java
map.put("A", 100);
map.put("B", 200);
```

Assume both go to bucket 3. Java **cannot overwrite** because keys are different. So it stores multiple entries there:

```text
Bucket[3]

[A,100] → [B,200]
```

Historically:

```text
Bucket
  |
  Node
  |
  Node
  |
  Node
```

This was a linked list.

Now lookup `map.get("B")`:

1. **Hash the key** — `"B"` → `hashCode()` → bucket 3
2. **Go to bucket 3** — it finds `[A,100]`, `[B,200]`
3. **Compare keys:**
   - `"A".equals("B")` → `false`
   - `"B".equals("B")` → `true` ✅
4. Returns `200`

### Java 8 improvement

If too many collisions happen in one bucket:

```text
Old:  A → B → C → D → E      Linked list lookup: O(n)
```

Java converts it into a **balanced tree**:

```text
       C
     /   \
    A     E
```

Lookup becomes **O(log n)**.

**Important interview line:**

> HashMap achieves average O(1) lookup by using hashing to locate buckets, and resolves collisions using linked lists or balanced trees internally.

> **➕ Added — HashMap internals interviewers love to ask**
>
> | Fact | Value |
> |---|---|
> | Default capacity | **16** buckets |
> | Load factor | **0.75** → when 75% full (12 entries), it **resizes** to 2× and **rehashes** every entry (O(n) that one time) |
> | Linked list → tree | when one bucket has **8+** entries (and table ≥ 64) |
> | Null keys | **one** `null` key allowed (goes to bucket 0); null values allowed |
> | Ordering | ❌ No guarantee — use `LinkedHashMap` if you need it |
> | Thread-safe? | ❌ No → use `ConcurrentHashMap` in multi-threaded code |
> | `put` same key again | **Overwrites** the old value (and returns the old one) |
> | Worst case lookup | O(n) before Java 8, **O(log n)** after |
>
> Handy methods:
>
> ```java
> map.getOrDefault("x", 0);
> map.putIfAbsent("x", 1);
> map.merge(word, 1, Integer::sum);          // word counting in one line
> for (Map.Entry<String, Integer> e : map.entrySet()) {
>     System.out.println(e.getKey() + " = " + e.getValue());
> }
> ```

---

## 9. 🔴 Objects as HashMap keys — `equals()` & `hashCode()`

One more thing to test, because this connects to the earlier "memory address" idea:

```java
class User {
    String name;
}

HashMap<User, Integer> map = new HashMap<>();

User u1 = new User();
u1.name = "Aadarsh";

map.put(u1, 100);
```

Later:

```java
User u2 = new User();
u2.name = "Aadarsh";

map.get(u2);
```

Do you expect it to return `100` or `null`? Think about `hashCode()` and `equals()`. (This is a **very common backend bug**.)

> 💬 **Me:** Excellent question. So my logic says map keys cannot be duplicates, but here we are adding `u1` or `u2` in map key — both are separate objects I think, even though both objects have same property [same name]. I think it will give **`null`**.
>
> But if it were `HashMap<String, Integer> map = new HashMap<>();` and `map.put(u1.name, 100);` were added, then `map.get(u2.name);` I think would have given **`100`**.

🔥 Excellent. Your reasoning is almost exactly right. This is the kind of thinking that helps with Java internals.

*u2 is a different object even though name is same → probably null* — ✅ Correct with the **default `Object` behavior**. Let's see why.

### Case 1: Object as key

```java
class User {
    String name;
}

User u1 = new User();
u1.name = "Aadarsh";

User u2 = new User();
u2.name = "Aadarsh";

map.put(u1, 100);

map.get(u2);
```

By default, Java uses `u1.hashCode()`, `u2.hashCode()` and `u1.equals(u2)`.

Default behavior from `Object`: `u1.equals(u2)` means *"Are these the exact same object in memory?"*

```text
u1 ───► User Object 1
u2 ───► User Object 2
```

Different objects. So `u1.equals(u2)` is `false`.

**Result: `null`** ✅ Your answer.

### Case 2: String as key

```java
map.put(u1.name, 100);

map.get(u2.name);
```

Now `u1.name` is `"Aadarsh"` and `u2.name` is also `"Aadarsh"`.

`String` **overrides** `equals()` and `hashCode()`, so `"Aadarsh".equals("Aadarsh")` is `true`.

**Result: `100`** ✅ Correct.

### This is why backend developers override `equals`/`hashCode`

```java
class User {

    String email;

    @Override
    public boolean equals(Object obj) {
        User other = (User)obj;
        return this.email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
```

Now:

```java
u1.email = "a@test.com";
u2.email = "a@test.com";
```

will behave like the **same key**.

> **➕ Added — the production-safe version of `equals()`**
>
> The version above is great for understanding, but crashes if `obj` is `null` or not a `User` (`ClassCastException`). The proper way:
>
> ```java
> @Override
> public boolean equals(Object o) {
>     if (this == o) return true;                    // same object
>     if (!(o instanceof User other)) return false;  // null or different type
>     return Objects.equals(email, other.email);     // null-safe compare
> }
>
> @Override
> public int hashCode() {
>     return Objects.hash(email);                    // null-safe, works for many fields
> }
> ```
>
> In real projects you rarely write this by hand: IntelliJ → *Generate → equals() and hashCode()*, Lombok's `@EqualsAndHashCode`, or a Java 16+ **record** which generates it automatically:
>
> ```java
> record User(String email, String name) {}   // equals, hashCode, toString, getters — all free
> ```

### ⭐ Very important interview rule

> **If you override `equals()`, you must override `hashCode()`.**

Because HashMap does:

```text
hashCode() → find bucket
equals()   → find exact key
```

If they disagree, HashMap breaks.

> **➕ Added — the `equals`/`hashCode` contract**
>
> - If `a.equals(b)` is `true` → `a.hashCode() == b.hashCode()` **must** be true.
> - Same `hashCode` does **not** mean equal (that's just a collision).
>
> Only `equals()` overridden → equal objects land in **different buckets** → `map.get()` returns `null`, `HashSet` keeps duplicates.

### Short version

By default, Java compares objects **by reference**.

```java
User u1 = new User("a@test.com");
User u2 = new User("a@test.com");

u1.equals(u2); // false by default
```

Even though data is same, they are different objects.

In backend, we usually care about **business equality**:

```text
User 1:  id = 101, email = a@test.com
User 2:  id = 101, email = a@test.com
```

Logically, they are the same user. So we override:

- `equals()` to define: *"When should two objects be considered the same?"*
- `hashCode()` to define: *"Where should this object go inside HashMap/HashSet?"*

### Example with `HashSet`

```java
Set<User> users = new HashSet<>();

users.add(u1);
users.add(u2);
```

- **Without override:** you get **2 users** — Java thinks they are different objects.
- **With `equals()` + `hashCode()`:** **1 user** — Java understands they are logically the same.

### Backend uses

- Avoid duplicate users in a `Set`
- Use `User` as a `HashMap` key
- Compare database entities

> **➕ Added — 🐛 never mutate a key after putting it in a map**
>
> ```java
> map.put(u1, 100);
> u1.email = "new@test.com";   // hashCode changes!
> map.get(u1);                 // null — it's looking in the wrong bucket now
> ```
>
> The entry is now "lost" inside the map. Use immutable keys (`String`, `Integer`, records). Another reason `String` being immutable matters.

---

## ➕ Added — other collection traps worth knowing

### Iterating & `ConcurrentModificationException`

```java
for (String name : names) {
    if (name.isEmpty()) names.remove(name);   // 💥 ConcurrentModificationException
}

names.removeIf(String::isEmpty);              // ✅ correct way
```

### Immutable lists

```java
List<String> fixed = List.of("a", "b");   // Java 9+
fixed.add("c");                            // 💥 UnsupportedOperationException

Arrays.asList("a", "b");                  // fixed SIZE (can set, can't add/remove)
```

### Program to the interface (same loose-coupling idea from file 02)

```java
List<String> names = new ArrayList<>();       // ✅ declare as interface
ArrayList<String> names = new ArrayList<>();  // ❌ tied to one implementation
```
