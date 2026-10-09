# 111. Can we modify the throws clause of the superclass method while overriding it in the subclass?

Yes, the throws clause can be modified while overriding a method, but certain rules must be followed. The subclass method can throw the same exception, a subclass of that exception, or no exception at all.

- The overridden method cannot throw a broader checked exception than the superclass method.
- It can throw the same exception or a more specific (subclass) checked exception.
- There are no such restrictions for unchecked exceptions (RuntimeException).

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#111-can-we-modify-the-throws-clause-of-the-superclass-method-while-overriding-it-in-the-subclass)
