# 135. What is EnumSet?

EnumSet is a specialized implementation of the Set interface for use with enumeration type. A few features of EnumSet are:

- It is non-synchronized.
- Faster than HashSet.
- All of the elements in an EnumSet must come from a single enumeration type.
- It doesn't allow null Objects and throws NullPointerException for exceptions.
- It uses a fail-safe iterator.

**Syntax:**

> public abstract class EnumSet\<E extends Enum\<E>>

**Parameter:** E specifies the elements.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#135-what-is-enumset)
