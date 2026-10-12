# 218. What happens when two interfaces contain identical default methods and a class implements both? How can it be resolved?

This is the **diamond problem**. The class **fails to compile**:

> `error: class Duck inherits unrelated defaults for sound() from types Bird and Animal`

```java
interface Animal { default String sound() { return "generic"; } }
interface Bird   { default String sound() { return "tweet"; } }

class Duck implements Animal, Bird {
    @Override
    public String sound() {                  // must override to resolve the conflict
        return Bird.super.sound();            // pick one...
        // or combine: Animal.super.sound() + "/" + Bird.super.sound()
        // or write a completely new implementation
    }
}
```

`InterfaceName.super.method()` calls a specific interface's default implementation.

**Resolution rules Java applies first** (the compile error only happens when these rules can't decide):
1. **Class wins**: a method from a superclass (or the class itself) beats any interface default.
2. **More specific interface wins**: if `Bird extends Animal` and both define `sound()`, `Bird`'s version is used automatically.
3. Otherwise the class **must override** explicitly.

The same rule applies if one interface has the method as `default` and the other has it as `abstract`: you must override.

---

[← Back to Medium index](README.md)
