# 02 · Interfaces, Abstract Classes, `super`, `final`, Exceptions

> **Legend:** `💬 Me:` = my own question/answer · `✅` = confirmed · **➕ Added** = extra notes added later to fill gaps

---

## 1. Interfaces

Interfaces are one of the most important Java concepts for backend development.

Forget syntax for a minute.

### Why do interfaces exist?

Suppose you build a payment system.

```text
Today:     Stripe
Tomorrow:  Stripe, Razorpay, PayPal
```

You don't want the rest of your code to care which payment provider is being used. So you define a **contract**.

```java
interface PaymentService {
    void pay();
}
```

This says: *"Anyone who claims to be a `PaymentService` must implement `pay()`."* No implementation yet.

### Implementation

```java
class StripePaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paying via Stripe");
    }
}
```

Another implementation:

```java
class RazorpayPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paying via Razorpay");
    }
}
```

Notice:

- `extends` → inherit from a **class**.
- `implements` → implement an **interface**.

### Usage

```java
PaymentService payment = new StripePaymentService();

payment.pay();
```

This should remind you of `Animal a = new Dog();` — same idea.

- Reference type = `PaymentService`
- Object type = `StripePaymentService`

> **➕ Added — why the implementations say `public void pay()`**
>
> Interface methods are **implicitly `public abstract`**. Since overriding can't reduce visibility, the implementing method **must be `public`**. Writing `void pay()` (package-private) in the class is a compile error — a very common beginner mistake.
>
> Also, any field in an interface is implicitly `public static final` (a constant).

---

## 2. Loose Coupling

This is a very common interview topic.

### Tight coupling ❌

```java
StripePaymentService payment = new StripePaymentService();
```

Your code now depends directly on Stripe. If tomorrow you want Razorpay, you have to change all places where `StripePaymentService` is used.

### Loose coupling ✅

```java
PaymentService payment = new StripePaymentService();
```

Your code depends only on the interface. Tomorrow you can do:

```java
PaymentService payment = new RazorpayPaymentService();
```

The rest of your code doesn't change because it only knows `payment.pay();` — not *how* payment is implemented.

> **➕ Added — this is exactly what Spring does (Dependency Injection)**
>
> In Spring you don't even write `new`. You ask for the interface, and Spring injects the implementation:
>
> ```java
> @Service
> class OrderService {
>     private final PaymentService payment;
>
>     OrderService(PaymentService payment) {   // Spring passes StripePaymentService here
>         this.payment = payment;
>     }
> }
> ```
>
> Swapping providers = changing config, not code. This is why interfaces are everywhere in Spring.

---

## 3. Interface vs Class — is implementing mandatory?

> 💬 **Me:** OK I got it — so if interface A has a method, anything implementing it should also have that method. But if it were a class, that is not mandatory?

✅ Exactly.

**If it were a class, is that mandatory?** No.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

}
```

This compiles. Why? Because `Dog` inherits `eat()` from `Animal`. It doesn't have to write it again.

If `Dog` wants different behavior, it can override it:

```java
class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("Dog eating");
    }
}
```

### Biggest difference

| Class (`extends`) | Interface (`implements`) |
|---|---|
| Gets implementation from parent | Gets only the contract |
| Can override if needed | Must provide the implementation (unless the class is abstract) |

**Interview one-liner:**

> A class provides **implementation + state**, whereas an interface primarily defines a **contract** that implementing classes must follow.

This distinction is fundamental in Java and comes up frequently in interviews.

> **➕ Added — modern interfaces can have code too (Java 8+)**
>
> ```java
> interface PaymentService {
>     void pay();                                   // abstract — must implement
>
>     default void refund() {                       // default — optional to override
>         System.out.println("Refund not supported");
>     }
>
>     static PaymentService stripe() {              // static helper
>         return new StripePaymentService();
>     }
> }
> ```
>
> So "interface = only contract" is *mostly* true. What interfaces still **can't** have: instance fields (state) and constructors.

---

## 4. Interface extending another interface

```java
interface Animal {
    void eat();
}

interface Pet extends Animal {
    void play();
}
```

Now any class implementing `Pet` must implement **both** methods.

```java
class Dog implements Pet {

    @Override
    public void eat() {
        System.out.println("Eating");
    }

    @Override
    public void play() {
        System.out.println("Playing");
    }
}
```

Think of it like:

```text
Animal
   ↑
  Pet
   ↑
Dog implements Pet
```

Since `Pet` extends `Animal`, `Dog` indirectly agrees to both contracts.

The difference is what `Animal` is:

- `class Animal` → inheritance (`extends`)
- `interface Animal` → implementation (`implements`)

### Why not `extends` an interface?

Because an interface is not a class. It has no object state to inherit from.

- Classes **extend** classes.
- Classes **implement** interfaces.
- Interfaces **extend** other interfaces.

### Quiz — which of these are valid?

```java
interface A {}
interface B {}

1  class C implements A {}
2  class C implements A, B {}
3  class C extends A {}
4  interface C extends A, B {}
```

> 💬 **Me:** 1, 2

Correct answer: ✅ **1, 2, and 4**

4 is valid because interfaces can extend **multiple** interfaces:

```java
interface A {}
interface B {}

interface C extends A, B {}
```

This is one of Java's design choices:

- ❌ A class cannot extend multiple classes.
- ✅ An interface can extend multiple interfaces.
- ✅ A class can implement multiple interfaces.

> **➕ Added — why no multiple class inheritance? (The Diamond Problem)**
>
> If `C extends A, B` and both `A` and `B` had a `hello()` method with different code, which one should `C` get? Java avoids this ambiguity by allowing only one parent class. With interface `default` methods the same clash can happen — Java then forces you to override and choose:
>
> ```java
> class C implements A, B {
>     @Override
>     public void hello() { A.super.hello(); }   // pick explicitly
> }
> ```

---

## 📍 Current level

- ✅ Classes
- ✅ Objects
- ✅ References
- ✅ Constructors
- ✅ `this`
- ✅ Access modifiers (basics)
- ✅ Inheritance
- ✅ Upcasting & Downcasting
- ✅ Method overriding
- ✅ Interfaces
- ✅ `extends` vs `implements`

This is roughly the point where many Java learners start feeling comfortable reading real code.

### 🗺️ Next roadmap (in order)

1. Abstract classes ⭐ (20 min)
2. `super` keyword
3. `final`
4. Exception Handling (try-catch, throws)
5. Collections (List, Set, Map) ← heavily used in backend
6. Generics
7. Lambda expressions
8. Streams API
9. JVM memory (Heap, Stack, GC)
10. Multithreading
11. Spring Boot

---

## 5. Abstract Class

Think of it as something **between a class and an interface**.

```java
abstract class Animal {

    void eat() {
        System.out.println("Eating");
    }

    abstract void speak();
}
```

Notice two things:

- `void eat() {}` → ✅ Has implementation.
- `abstract void speak();` → ❌ No implementation.

An abstract class can have **both**.

```java
class Dog extends Animal {

    @Override
    void speak() {
        System.out.println("Bark");
    }
}
```

`Dog` **must** implement `speak()` because it's abstract. But it inherits `eat()` automatically.

### Can we do this?

```java
Animal a = new Animal();
```

❌ **No.** Because an abstract class is incomplete. It's like saying: *"There will be different kinds of Animals, but I don't know how every Animal speaks."* So Java doesn't let you create an object of it.

### Mental model

| Class | Abstract Class | Interface |
|---|---|---|
| Complete implementation | Partial implementation | Contract |
| ✅ Can create object | ❌ Cannot create object | ❌ Cannot create object |

### Interview rule (don't memorize, understand)

Use an **abstract class** when subclasses share common **state or behavior**:

```java
abstract class Animal {
    String name;

    void eat() {
        System.out.println("Eating");
    }

    abstract void speak();
}
```

Every animal has a `name` and can `eat()`, but each animal `speak()`s differently.

An **interface** is better when you want **unrelated classes** to follow the same contract.

> **➕ Added — full Abstract class vs Interface comparison**
>
> | | Abstract class | Interface |
> |---|---|---|
> | Keyword for child | `extends` | `implements` |
> | How many can you inherit? | Only **one** | **Many** |
> | Instance fields (state) | ✅ Yes | ❌ Only constants (`public static final`) |
> | Constructors | ✅ Yes (called via `super()`) | ❌ No |
> | Method types | Abstract + concrete, any access modifier | Abstract, `default`, `static`, `private` |
> | Relationship | "**is-a**" (Dog *is an* Animal) | "**can-do**" (Dog *can be* Comparable, Serializable) |
>
> Yes — an abstract class **can have a constructor** even though you can't `new` it. It runs when a subclass object is created.

---

## 6. Abstract — rules

### 6.1 If a class has even one abstract method

```java
abstract class Animal {
    abstract void speak();
}
```

Then `Animal a = new Animal();` ❌ Not allowed.

The reason is **not** "because it has one abstract method." The reason is: **the class itself is declared `abstract`.**

In Java, if a class contains an abstract method, the class **must** be declared abstract.

> **➕ Added:** The reverse isn't required — a class can be `abstract` with **zero** abstract methods (just to prevent direct instantiation).

### 6.2 Is there an abstract variable?

❌ No. Only these can be abstract:

- Classes
- Methods

Fields (variables) **cannot** be abstract.

### 6.3 Quiz

```java
abstract class Animal {
    abstract void speak();
}

class Dog extends Animal {

}
```

❌ **It will NOT compile.**

Why? `Dog` is a concrete class (not abstract), so it must implement all inherited abstract methods. You have two options:

```java
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Bark");
    }
}
```

or

```java
abstract class Dog extends Animal {

}
```

because now `Dog` is also abstract and is allowed to leave `speak()` unimplemented.

### Rule (worth remembering)

If a class extends an abstract class, it must do **one** of these:

1. Implement all abstract methods.
2. Declare itself abstract.

> 💬 **Me:** OK now I get it — but if it were a `public class Animal` with public method `speak` then it would have compiled. But here as `Dog` is a normal class it acts as an implement thing — you need that method in the `Dog` class for compilation, or just make `Dog` an abstract class.
>
> **➕ Added — small clarification:** `public` isn't what makes the difference. What matters is whether `Animal` is **abstract** and `speak()` has **no body**. If `Animal` were a normal (non-abstract) class with `void speak() { ... }` implemented, `Dog` would compile without writing `speak()` — regardless of `public`.

---

## 7. `super`

### Important fact (interview favorite)

Even if you don't write `super()`, Java **inserts it automatically**. So these are equivalent:

```java
Dog() {
    super();
    System.out.println("Dog");
}
```

and

```java
Dog() {
    System.out.println("Dog");
}
```

The compiler secretly adds `super();` as the **first statement**.

The only time you write it yourself is when the parent has a constructor **with parameters**:

```java
class Animal {
    Animal(String name) {}
}

class Dog extends Animal {
    Dog() {
        super("Dog");
    }
}
```

because there is no no-argument constructor to call.

### Tiny quiz (last one on `super`)

```java
class Animal {
    Animal(String name) {}
}

class Dog extends Animal {
    Dog() {

    }
}
```

Does it compile? A. Yes · B. No · Why? (One line.)

> 💬 **Me:** No it won't, as the compiler cannot add `super()` since the super class constructor has a parameter `name`.

🎯 Perfect. That's exactly right.

**Interview answer:**

> No. The compiler inserts `super()` only if the parent has a no-argument constructor. Since `Animal` only has `Animal(String name)`, `Dog` must explicitly call `super("...")`.

```java
class Animal {
    Animal(String name) {}
}

class Dog extends Animal {
    Dog() {
        super("Dog");
    }
}
```

> **➕ Added — the other uses of `super`**
>
> `super` isn't only for constructors. It also calls the **parent's version of a method**:
>
> ```java
> class Dog extends Animal {
>     @Override
>     void speak() {
>         super.speak();               // runs Animal's speak() first
>         System.out.println("Bark");
>     }
> }
> ```
>
> | | `this` | `super` |
> |---|---|---|
> | Refers to | Current object | Parent part of current object |
> | `this()` / `super()` | Call another constructor of **same** class | Call **parent** constructor |
> | `.method()` | Current class's method | Parent class's method |
>
> `this()` and `super()` must both be the **first** statement → you can't use both in the same constructor.
>
> **Constructor order:** parent constructor always runs **before** child constructor (`new Dog()` → `Animal()` body → `Dog()` body).

---

## 8. `final`

This keyword has **3 different meanings** depending on where it's used.

### 8.1 final variable

```java
final int age = 25;
age = 30;   // ❌
```

Once assigned, it cannot be reassigned.

### 8.2 final method

```java
class Animal {
    final void eat() {}
}

class Dog extends Animal {
    void eat() {}   // ❌
}
```

A final method **cannot be overridden**.

### 8.3 final class

```java
final class Animal {}

class Dog extends Animal {}   // ❌
```

A final class **cannot be extended**. Example: `String` in Java is final.

### ⚠️ Interview trap

```java
final Car c = new Car();
```

| Can we do... | |
|---|---|
| `c = new Car();` | ❌ No |
| `c.brand = "BMW";` | ✅ Yes |

Why? Because `final` makes the **reference variable** immutable, **not the object**.

```text
final c  ─────► Car Object
```

You cannot make `c` point somewhere else, but you can still modify the object it points to (unless the object's fields are also immutable).

> **➕ Added — `final` in real code**
>
> ```java
> public static final int MAX_RETRIES = 3;     // constant (UPPER_SNAKE_CASE)
> private final PaymentService payment;       // Spring constructor injection — set once
> ```
>
> - A `final` field must be assigned exactly once — either at declaration or in **every** constructor.
> - `final` ≠ immutable: `final List<String> list = new ArrayList<>(); list.add("x");` ✅ works.
> - **`final` vs `finally` vs `finalize`** — classic trick question: keyword / try-catch block / deprecated GC hook. Unrelated.

### Why is `String` final?

> 💬 **Me:** (guessed it's because String is a data type)

Not exactly.

`String` isn't final because it's a data type. It's final because Java wants strings to be **immutable and safe**.

If inheritance were allowed:

```java
class MyString extends String {
    // imagine changing behavior...
}
```

someone could change how strings behave, breaking assumptions across the JVM.

Benefits of `String` being immutable/final include:

- **Security** (file paths, URLs, passwords, class names can't be altered unexpectedly)
- **Thread safety**
- **String Pool** optimization
- **Reliable `hashCode()`** (important for `HashMap`)

You don't need to memorize all of these now. Just remember:

> `String` is final so its behavior cannot be changed through inheritance, helping keep strings immutable and safe.

> **➕ Added — immutability in practice**
>
> ```java
> String s = "hello";
> s.toUpperCase();          // creates a NEW string, s is still "hello"
> s = s.toUpperCase();      // now s points to the new "HELLO"
> ```
>
> Concatenating in a loop (`s += x`) creates a new String every time → use `StringBuilder`:
>
> ```java
> StringBuilder sb = new StringBuilder();
> for (int i = 0; i < 1000; i++) sb.append(i);
> String result = sb.toString();
> ```

---

## 9. Exceptions

```java
int a = 10;
int b = 0;

System.out.println(a / b);
```

✅ Correct. **C. Runtime exception.** More precisely:

```text
ArithmeticException: / by zero
```

Java throws an exception at runtime because the operation is illegal.

### What is an Exception?

An exception is an **object** that represents an error occurring during program execution.

```text
Program running
      │
      ▼
Something unexpected happens
      │
      ▼
Java creates an Exception object
      │
      ▼
Either you handle it or the program terminates
```

### `try` / `catch` / `finally`

```java
try {
    System.out.println("A");
} catch (Exception e) {
    System.out.println("B");
} finally {
    System.out.println("C");
}
```

> **➕ Added — output:** `A` then `C` (no exception, so `catch` is skipped; `finally` always runs).

And your explanation is interview-worthy:

> `finally` executes **regardless** of whether an exception occurs or is caught.

One small caveat: there are rare cases (e.g., `System.exit()` or JVM crash) where `finally` won't execute, but ignore those for now.

> **➕ Added — exception hierarchy (know this picture)**
>
> ```text
>                  Throwable
>                 /         \
>             Error        Exception
>     (JVM problems:      /          \
>  OutOfMemoryError,  IOException   RuntimeException
>  StackOverflowError) SQLException     /       |        \
>     ❌ don't catch   (CHECKED)   NullPointer  Arithmetic  IllegalArgument ...
>                                      (UNCHECKED)
> ```
>
> - Everything under `RuntimeException` → **unchecked**.
> - Everything else under `Exception` → **checked**.
> - `Error` → serious JVM problems; don't catch these.

> **➕ Added — multiple catch blocks**
>
> ```java
> try {
>     ...
> } catch (FileNotFoundException e) {   // specific FIRST
>     ...
> } catch (IOException e) {             // general AFTER
>     ...
> } catch (IllegalArgumentException | NullPointerException e) {  // multi-catch (Java 7+)
>     ...
> }
> ```
>
> Putting the general one (`Exception`) first is a **compile error** — the specific ones would be unreachable.

---

## 10. `throw` vs `throws`

### `throws`

You declare that a method **may** throw an exception.

```java
void readFile() throws IOException {
    ...
}
```

Think: *"I'm warning the caller that this method might throw an `IOException`."* The caller must then decide whether to handle it or propagate it.

### Easy way to remember

- `throw` → **inside** a method, throws **one** exception object.
- `throws` → in the method **signature**, **declares** possible exceptions.

### Correction: built-in vs custom exception

```java
throw new IOException();
```

❌ This is **not** a custom exception. `IOException` is a built-in Java exception.

A custom exception would be something **you** create:

```java
class InvalidAgeException extends Exception {
}
```

Then:

```java
throw new InvalidAgeException();
```

> **➕ Added — a proper custom exception (how you'd write it in a backend)**
>
> ```java
> class UserNotFoundException extends RuntimeException {   // unchecked — most common in Spring
>     public UserNotFoundException(String email) {
>         super("User not found: " + email);                // message → e.getMessage()
>     }
> }
>
> throw new UserNotFoundException("a@test.com");
> ```
>
> - `extends Exception` → checked (callers forced to handle).
> - `extends RuntimeException` → unchecked (Spring style; a global `@ControllerAdvice` handler turns it into a 404, etc.).

### Why do we need `throws`?

```java
void readFile() throws IOException {
    // code that may fail while reading a file
}
```

You're telling Java: *"This method might throw an `IOException`. I'm not handling it here."*

Now another method calls it:

```java
void process() {
    readFile();
}
```

Java says: ❌ *"Hold on. `readFile()` might throw an `IOException`. What are you going to do if that happens?"*

So you have two choices.

**Option 1: Handle it**

```java
void process() {
    try {
        readFile();
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
```

**Option 2: Pass the responsibility**

```java
void process() throws IOException {
    readFile();
}
```

Now `process()` is also telling its caller: *"I don't handle it either. You deal with it."*

The responsibility keeps moving up until someone catches it.

### Should every method have `throws`?

❌ No. Only methods that can throw **checked** exceptions and choose **not** to handle them.

If your method can handle the exception itself, use `try-catch` and don't declare `throws`.

### Mental model

Think of `throws` as **forwarding a package**.

```text
readFile() ──► process() ──► main()

       IOException
```

Each method can either:

- 📦 Open the package (`try-catch`), or
- 📦 Pass it to the next person (`throws`).

> **➕ Added — try-with-resources (Java 7+, use this for files/DB/streams)**
>
> ```java
> try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
>     System.out.println(br.readLine());
> } catch (IOException e) {
>     e.printStackTrace();
> }
> // br.close() is called automatically — no finally needed
> ```
>
> Works with anything implementing `AutoCloseable`.

> **➕ Added — exception best practices**
>
> - ❌ Never swallow: `catch (Exception e) {}` hides bugs.
> - ❌ Don't catch `Exception` / `Throwable` blindly — catch what you can actually handle.
> - ✅ Log or rethrow with context: `throw new ServiceException("Payment failed", e);` (keep the original `e` as the *cause*).
> - ✅ Don't use exceptions for normal control flow.

---

## 11. Checked vs Unchecked Exceptions

This is asked in almost every Java interview.

### 11.1 Checked Exceptions

The compiler **forces** you to handle them. Examples:

- `IOException`
- `SQLException`

If a method throws one of these:

```java
void readFile() throws IOException
```

then the caller must either:

- use `try-catch`, or
- declare `throws IOException`.

Otherwise, it won't compile.

### 11.2 Unchecked Exceptions

The compiler does **not** force you to handle them. Examples:

- `ArithmeticException`
- `NullPointerException`
- `IndexOutOfBoundsException`
- `IllegalArgumentException`

You *can* catch them, but you don't have to.

```java
int x = 10 / 0;
```

This compiles fine, but at runtime: `ArithmeticException`.

### Easy way to remember

| Checked | Unchecked |
|---|---|
| Compiler checks | Compiler doesn't check |
| Must handle or declare | Optional to handle |
| Usually external failures (files, DB, network) | Usually programming bugs |
| Extends `Exception` | Extends `RuntimeException` |

---

## 12. Compile time vs Runtime

### Compile time

This happens when Java converts `.java → .class` using:

```bash
javac Main.java
```

The compiler checks things like:

- Syntax errors
- Type errors
- Missing methods
- Access modifier violations
- Checked exceptions not handled

```java
int x = "hello";
```

❌ Compile error. Program never runs.

### Runtime

This happens when you execute:

```bash
java Main
```

The JVM is running your program. Errors that happen now:

```java
int x = 10 / 0;
```

Compiles ✅ · Runs ❌ → `ArithmeticException`

### Should we handle both?

No. They are different categories.

You don't "handle" compile errors. You **fix** them.

```java
String name = 100;
```

You don't catch this with `try-catch`. You correct the code.

Runtime exceptions **can** be handled:

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Invalid division");
}
```

**Interview summary:**

> Compile-time errors are detected by the compiler before execution. Runtime errors occur while the program is running and may be handled using exception handling.

> **➕ Added — JDK vs JRE vs JVM (often asked right after this)**
>
> ```text
> JDK  = JRE + dev tools (javac, jar, debugger)     ← you install this to WRITE Java
> JRE  = JVM + standard libraries                   ← needed to RUN Java
> JVM  = runs the .class bytecode on any OS         ← "write once, run anywhere"
> ```
>
> `.java` → (`javac`) → `.class` bytecode → (JVM) → machine code. The bytecode is the same on every OS; only the JVM differs.
