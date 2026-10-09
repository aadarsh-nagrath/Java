# 04 · Generics, Wrapper Classes & Autoboxing

> **Legend:** `💬 Me:` = my own question/answer · `✅` = confirmed · **➕ Added** = extra notes added later to fill gaps

---

## 1. Generics

You've already been using them:

```java
List<String> names = new ArrayList<>();

Map<String, User> users = new HashMap<>();
```

The `<String>` and `<User>` are **generics**.

### Problem without generics

```java
ArrayList list = new ArrayList();

list.add("Aadarsh");
list.add(100);

String name = (String) list.get(1); // 💥 Runtime error
```

Because the list can store **anything**.

### With generics

```java
ArrayList<String> list = new ArrayList<>();

list.add("Aadarsh");
list.add(100); // ❌ Compile error
```

Now Java protects you **earlier** (at compile time instead of runtime).

> **➕ Added:** The empty `<>` on the right side is called the **diamond operator** (Java 7+). Java infers the type from the left: `new ArrayList<>()` = `new ArrayList<String>()`.

---

## 2. Generics work with objects, not primitives

This is invalid:

```java
List<int> nums;   // ❌
```

Because `int` is primitive. Use **wrapper classes**:

```java
List<Integer> nums;  // ✅
```

> **➕ Added — primitive → wrapper table**
>
> | Primitive | Wrapper |
> |---|---|
> | `int` | `Integer` |
> | `long` | `Long` |
> | `double` | `Double` |
> | `float` | `Float` |
> | `boolean` | `Boolean` |
> | `char` | `Character` |
> | `byte` | `Byte` |
> | `short` | `Short` |

---

## 3. Autoboxing

Java automatically converts:

```java
int x = 10;
list.add(x);
```

to:

```java
Integer.valueOf(10);
```

This is called **autoboxing**. (The reverse — `Integer` → `int` — is called **unboxing**.)

### A small deeper point

These are different:

```java
int a = 10;       // Primitive stored directly.
Integer b = 10;   // Object created (autoboxing).
```

Java converts `Integer b = 10;` into something like:

```java
Integer b = Integer.valueOf(10);
```

### ⭐ One important interview point

> **Generics give compile-time type safety.**

> **➕ Added — 🐛 two classic wrapper bugs**
>
> **1. Comparing `Integer` with `==`**
>
> ```java
> Integer a = 127, b = 127;
> System.out.println(a == b);       // true  😮
>
> Integer c = 128, d = 128;
> System.out.println(c == d);       // false 😱
>
> System.out.println(c.equals(d));  // true  ✅ always use equals()
> ```
>
> Why? `Integer.valueOf()` **caches** values from **-128 to 127**, so those return the same object. Outside that range, new objects are created → `==` compares references → `false`. Same lesson as Strings: **compare objects with `.equals()`**.
>
> **2. Unboxing `null` → `NullPointerException`**
>
> ```java
> Map<String, Integer> stock = new HashMap<>();
> int qty = stock.get("apple");   // 💥 NPE — get() returns null, can't unbox null to int
>
> int qty = stock.getOrDefault("apple", 0);   // ✅
> ```

---

## ➕ Added — writing your own generics

So far you've *used* generics. In real code you'll also *write* them.

### Generic class

```java
class Box<T> {                 // T = "Type" placeholder
    private T value;

    void set(T value) { this.value = value; }
    T get() { return value; }
}

Box<String> b = new Box<>();
b.set("hello");
String s = b.get();            // no cast needed
```

Common naming: `T` (type), `E` (element), `K`/`V` (key/value), `R` (return).

A backend example you'll see everywhere:

```java
class ApiResponse<T> {
    boolean success;
    T data;
}

ApiResponse<User> res;          // data is a User
ApiResponse<List<Order>> res2;  // data is a List<Order>
```

### Generic method

```java
static <T> T firstOf(List<T> list) {
    return list.get(0);
}

String name = firstOf(List.of("a", "b"));   // T inferred as String
Integer n   = firstOf(List.of(1, 2));       // T inferred as Integer
```

The `<T>` before the return type declares the type parameter for that method.

### Bounded types — `extends`

Restrict which types are allowed:

```java
static <T extends Number> double sum(List<T> nums) {
    double total = 0;
    for (T n : nums) total += n.doubleValue();   // allowed because T is a Number
    return total;
}

sum(List.of(1, 2, 3));        // ✅ Integer is a Number
sum(List.of("a"));            // ❌ compile error
```

### Wildcards — `?`

```java
List<Integer> ints = List.of(1, 2);
List<Number> nums = ints;          // ❌ compile error! List<Integer> is NOT a List<Number>
List<? extends Number> ok = ints;  // ✅
```

| Wildcard | Meaning | You can |
|---|---|---|
| `List<?>` | list of some unknown type | read as `Object` |
| `List<? extends Number>` | Number or any **subclass** | **read** (get Numbers out) |
| `List<? super Integer>` | Integer or any **superclass** | **write** (put Integers in) |

Memory trick — **PECS: Producer `extends`, Consumer `super`.**

### Type erasure (interview question)

Generics exist **only at compile time**. After compiling, `List<String>` and `List<Integer>` are both just `List` in bytecode. Consequences:

```java
new T();                          // ❌ can't create an instance of T
if (list instanceof List<String>) // ❌ type info is gone at runtime
```

That's why generics give **compile-time** type safety — the check happens before the program runs, then the type info is erased.

---

## ➕ Added — trap: `List.remove(int)` vs `remove(Object)`

```java
List<Integer> nums = new ArrayList<>(List.of(10, 20, 30));

nums.remove(1);                    // removes INDEX 1 → [10, 30]
nums.remove(Integer.valueOf(10));  // removes VALUE 10 → [30]
```

With an `int` argument Java picks the index version (no autoboxing needed), not the value one.
