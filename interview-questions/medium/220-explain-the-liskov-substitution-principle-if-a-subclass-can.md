# 220. Explain the Liskov Substitution Principle. If a subclass can replace its parent, why is inheritance required?

**LSP:** *If `S` is a subtype of `T`, objects of type `T` can be replaced with objects of type `S` without altering the correctness of the program.* A subclass must honour the parent's **contract**:
- It must not strengthen preconditions (accept less) or weaken postconditions (promise less).
- It must keep the parent's invariants.
- It must not throw new unexpected exceptions or silently do nothing.

**Classic violation, Rectangle/Square**
```java
class Rectangle { void setWidth(int w); void setHeight(int h); int area(); }
class Square extends Rectangle {
    void setWidth(int w)  { super.setWidth(w); super.setHeight(w); }   // surprises callers
}

void resize(Rectangle r) { r.setWidth(5); r.setHeight(4); assert r.area() == 20; }   // fails for Square
```
Other examples: `ReadOnlyList extends ArrayList` throwing on `add()`, or `Penguin extends Bird` with `fly()` throwing.
Fix: model the shared abstraction correctly (`interface Shape { int area(); }`; `FlyingBird` vs `Bird`).

### "If a subclass can replace its parent, why do we need inheritance?"
The question mixes up cause and effect. **Inheritance (or interface implementation) is what makes substitution possible in the first place.** LSP is a *rule about how to inherit correctly*, not an alternative to inheritance.

- **Inheritance** gives you the *is-a* relationship, polymorphism (`List<Shape>` holding circles and squares) and code reuse.
- **LSP** tells you when that *is-a* relationship is **valid**: being substitutable is the test that an inheritance hierarchy should pass.
- Without inheritance or interfaces there's no shared type, so there would be nothing to substitute.

In practice LSP often pushes you toward **interfaces + composition** rather than deep class inheritance. You still need a subtype relationship, but you inherit the *contract*, not the implementation.

---

[← Back to Medium index](README.md)
