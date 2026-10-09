# 01 · OOP Basics — Packages, Classes, Objects, Polymorphism

> **Legend:** `💬 Me:` = my own question/answer · `✅` = confirmed · **➕ Added** = extra notes added later to fill gaps

---

## 1. Packages, Classes, Objects

Think of it like folders.

```text
com.example.app      ← package
    ├── Car.java     ← class
    ├── User.java    ← class
    └── Main.java    ← class
```

Objects are created at runtime from classes.

```text
Package
   ↓
Classes
   ↓
Objects (created when program runs)
```

Objects are **not** inside the class. The class is just code. Objects live in memory while the program runs.

---

## 2. `String[] args`

When you run:

```bash
java Main hello world 123
```

The JVM calls:

```java
main(new String[]{"hello", "world", "123"});
```

So inside `main`:

```java
args[0] = "hello"
args[1] = "world"
args[2] = "123"
```

That's all it is. The JVM passes command-line arguments as a `String[]`.

> **➕ Added — the full `main` signature, word by word**
>
> ```java
> public static void main(String[] args)
> ```
>
> | Part | Why it's there |
> |---|---|
> | `public` | The JVM (outside your class) must be able to call it |
> | `static` | JVM calls it **without creating an object** of `Main` |
> | `void` | Returns nothing to the JVM |
> | `main` | The exact name the JVM looks for |
> | `String[] args` | Command-line arguments |

---

## 3. `@SpringBootApplication`

This is called an **annotation**.

Annotations are metadata that tell frameworks something.

`@SpringBootApplication` → *"This is the main Spring Boot application. Start scanning for components, configure defaults, and boot the application."*

---

## 4. Objects & Constructors

This line:

```java
Car c1 = new Car();
```

creates **one object** in memory.

This:

```java
new Car()
```

calls the **constructor**.

The **default constructor** is being called because you didn't define one.

> **➕ Added — reference variables**
>
> `c1` is not the object — it's a **reference** (an arrow) pointing to the object.
>
> ```java
> Car c1 = new Car();
> Car c2 = c1;          // NO new object! both point to the same Car
> c2.brand = "BMW";
> System.out.println(c1.brand); // BMW
> ```
>
> ```text
> c1 ──┐
>      ├──► Car Object
> c2 ──┘
> ```
>
> - Only `new` creates objects.
> - The default constructor exists **only if you write no constructor at all**. Once you write `Car(String b)`, `new Car()` stops compiling (unless you also write `Car()`).

---

## 5. Types of Variables

There are a few types of variables. Don't memorize them yet — just understand them.

### 5.1 Instance Variable (Field)

Belongs to **each object**.

```java
class Car {
    String brand;   // Instance variable
}
```

```java
Car c1 = new Car();
Car c2 = new Car();

c1.brand = "BMW";
c2.brand = "Audi";
```

Each object has its own copy of `brand`.

### 5.2 Local Variable

Declared inside a method or constructor.

```java
Car(String brand) {   // <-- brand is a local variable (parameter)
    this.brand = brand;
}
```

or

```java
void start() {
    int speed = 100;   // Local variable
}
```

Local variables exist only while that method/constructor is executing.

> **➕ Added:** Fields get default values (`0`, `false`, `null`). Local variables do **not** — using one before assigning it is a compile error.

### 5.3 Static Variable (Class Variable)

**Shared** by all objects.

```java
class Car {
    static int count = 0;
}
```

There's only one `count`, no matter how many `Car` objects you create.

We'll cover `static` in more detail later.

---

## 6. `this.brand = brand`

```java
class Car {
    String brand;          // Instance variable

    Car(String brand) {    // Local variable (parameter)
        this.brand = brand;
    }
}
```

- `this.brand` → the object's field.
- `brand` → the constructor parameter.

So you're copying the parameter into the object's field.

### Quick quiz

```java
class Car {
    String brand;

    Car(String b) {
        brand = b;
    }
}
```

Is `this` required here?

- A. Yes
- B. No

> **➕ Added — answer: B. No.** The names are different (`brand` vs `b`), so there's no confusion. `this` is only *required* when a parameter/local has the **same name** as a field (shadowing).

### Progress

- ✅ Classes
- ✅ Objects
- ✅ Reference variables
- ✅ `new`
- ✅ Constructors
- ✅ Difference between constructor and method

> **➕ Added — constructor vs method**
>
> | | Constructor | Method |
> |---|---|---|
> | Name | Same as class | Anything |
> | Return type | None (not even `void`) | Required |
> | Called | Automatically by `new` | Explicitly by you |
> | Inherited? | ❌ No | ✅ Yes |
> | Purpose | Initialize the object | Behavior |
>
> **Constructor overloading + `this()` chaining:**
>
> ```java
> class Car {
>     String brand;
>     int year;
>
>     Car() { this("Unknown"); }            // calls Car(String)
>     Car(String brand) { this(brand, 2024); }
>     Car(String brand, int year) {
>         this.brand = brand;
>         this.year = year;
>     }
> }
> ```
>
> `this(...)` must be the **first** statement in a constructor.

---

## 7. Access Modifiers

This is heavily used in Java and Spring. There are 4:

| Modifier | Accessible from |
|---|---|
| `public` | Everywhere |
| `protected` | Same package + subclasses (it includes other classes in the same package too) |
| *(default)* | Same package only |
| `private` | Same class only |

### Where can access modifiers be used?

| Thing | public | protected | default | private |
|---|:---:|:---:|:---:|:---:|
| Top-level class | ✅ | ❌ | ✅ | ❌ |
| Field | ✅ | ✅ | ✅ | ✅ |
| Method | ✅ | ✅ | ✅ | ✅ |
| Constructor | ✅ | ✅ | ✅ | ✅ |
| Object (`User u = ...`) | ❌ | ❌ | ❌ | ❌ |

Objects do not have access modifiers. Variables like `User u` don't either.

> **➕ Added:** Local variables can't have access modifiers either (they only exist inside the method anyway). One file can have only **one `public` top-level class**, and its name must match the file name.

### `private`

✅ Correct.

`private` means only code inside that class can access it. That excludes:

- ❌ Other classes in the same package
- ❌ Subclasses
- ❌ Any other class anywhere

> **➕ Added — Encapsulation (why `private` matters)**
>
> Standard practice: fields `private`, access via public methods. This lets the class protect its own data.
>
> ```java
> class BankAccount {
>     private double balance;
>
>     public double getBalance() { return balance; }
>
>     public void deposit(double amount) {
>         if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
>         balance += amount;
>     }
> }
> ```
>
> Nobody can do `account.balance = -1000;`. (In Spring projects, Lombok's `@Getter/@Setter` generates these.)

---

## 8. Can a constructor and a method have the same name?

Yes.

```java
class User {
    User() {}          // Constructor
    void User() {}     // Method
}
```

This is valid Java, although it's considered bad practice because it's confusing.

The compiler distinguishes them because:

- Constructor → **no** return type
- Method → **has** a return type (`void` here)

---

## 9. Polymorphism — Reference type vs Object type

> 💬 **Me:** Say we have:
>
> ```java
> class Animal {
>     void speak() {
>         System.out.println("Animal sound");
>     }
> }
>
> class Dog extends Animal {
>     @Override
>     void speak() {
>         System.out.println("Bark");
>     }
>     void wiggletail() {}
> }
> ```
>
> and we create `Animal a = new Dog();` somewhere — so `a.speak()` will be `Bark` right? But `a.wiggletail()` is not possible cuz that is not in `Animal` and `a` is reference `Animal`?

🎯 **YES. Exactly.** You just understood one of the most important concepts in Java.

```java
class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Bark");
    }

    void wiggleTail() {}
}

Animal a = new Dog();
```

Now:

```java
a.speak();
```

✅ Compiles because `Animal` has `speak()`. At runtime it prints `Bark` because the actual object is a `Dog`.

```java
a.wiggleTail();
```

❌ **Compile error.**

Why? The compiler checks: *"Does `Animal` have a method called `wiggleTail()`?"* No. So it refuses to compile.

### 🧠 Mental model

> The **reference type** decides: *"What methods am I allowed to call?"*
>
> The **object type** decides: *"Which implementation of that method actually runs?"*

This one sentence is worth remembering.

> **➕ Added — what polymorphism does NOT apply to**
>
> Only **instance methods** are chosen by object type at runtime. These are decided by the **reference type**:
>
> - **Fields** — `a.name` uses `Animal`'s field even if `Dog` declares its own `name`.
> - **`static` methods** — they're *hidden*, not overridden.
> - **`private` methods** — not inherited, so can't be overridden.

---

## 10. `@Override`

It tells the compiler: *"I intend to override a parent method."*

If you accidentally make a mistake:

```java
class Dog extends Animal {
    @Override
    void speek() { }   // typo
}
```

The compiler immediately errors because there is no `speek()` in `Animal` to override.

Without `@Override`, this typo would silently create a new method — harder to catch.

> **➕ Added — Overriding vs Overloading (interview classic)**
>
> | | Overriding | Overloading |
> |---|---|---|
> | Where | Parent ↔ child class | Same class (or inherited) |
> | Signature | **Same** name + same params | Same name, **different** params |
> | Decided at | Runtime (object type) | Compile time (argument types) |
> | Example | `Dog.speak()` replaces `Animal.speak()` | `add(int,int)` / `add(double,double)` |
>
> Overriding rule: you **cannot reduce visibility** (parent `public` → child can't be `protected`/`private`).

---

## 11. Quiz — What prints?

```java
class Animal {
    void speak() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog");
    }
}

Animal a = new Dog();
Dog d = new Dog();

a.speak();
d.speak();
```

> 💬 **Me:** `a.speak()` → Dog, `d.speak()` → Dog

✅ Correct. Output:

```text
Dog
Dog
```

---

## 12. Trickier one — Casting

```java
class Animal {
    void speak() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog");
    }

    void wagTail() {
        System.out.println("Wagging");
    }
}

Animal a = new Dog();
```

What happens?

```java
((Dog) a).wagTail();
```

- A. Compile error
- B. Runtime error
- C. Prints `Wagging`

This introduces **casting**, which builds directly on what you already learned.

> 💬 **Me:** Here `a` is reference to `Animal` but object is `Dog`. If it was `a.wagTail();` then it would not have compiled as there is no `wagTail()` in `Animal`, but `speak` would have worked [overriding method in Animal class by Dog class, that's why]. But since it is `((Dog) a).wagTail();` it downcasts Animal to Dog, so answer will be **"Wagging"**.

🎯 **100% correct.** That's exactly how I'd expect a mid-level Java developer to reason.

- `a.wagTail()` won't compile because the reference type is `Animal`. ✅
- `((Dog) a)` downcasts the reference to `Dog`. ✅
- Since the object is actually a `Dog`, it prints `Wagging`. ✅

**One small addition:**

```java
Animal a = new Animal();
((Dog) a).wagTail();
```

It **compiles**, but at runtime you'll get `ClassCastException` because the object is not a `Dog`.

> **➕ Added — Upcasting vs Downcasting + safe casting**
>
> ```java
> Animal a = new Dog();   // Upcasting   → automatic, always safe
> Dog d = (Dog) a;        // Downcasting → explicit, can fail at runtime
> ```
>
> Check before downcasting with `instanceof`:
>
> ```java
> if (a instanceof Dog) {
>     ((Dog) a).wagTail();
> }
>
> // Java 16+ pattern matching — check + cast in one go
> if (a instanceof Dog d) {
>     d.wagTail();
> }
> ```

---

## ➕ Added — Extra things worth knowing at this stage

### The 4 pillars of OOP (interview staple)

| Pillar | Meaning | Where you saw it |
|---|---|---|
| **Encapsulation** | Hide data, expose controlled methods | `private` fields + getters |
| **Inheritance** | Child reuses parent code | `Dog extends Animal` |
| **Polymorphism** | Same call, different behavior | `Animal a = new Dog(); a.speak()` |
| **Abstraction** | Show *what*, hide *how* | Interfaces / abstract classes (next file) |

### Java is always pass-by-value

Java copies the **value of the reference**, not the object.

```java
void rename(Car c) { c.brand = "Audi"; }   // ✅ changes the original object
void replace(Car c) { c = new Car(); }     // ❌ caller's variable unaffected
```

### `==` vs `.equals()`

```java
String a = new String("hi");
String b = new String("hi");
a == b        // false → are they the SAME object?
a.equals(b)   // true  → do they have the SAME content?
```

Always compare Strings (and most objects) with `.equals()`. (Covered deeper in [03-collections-hashmap.md](03-collections-hashmap.md).)
