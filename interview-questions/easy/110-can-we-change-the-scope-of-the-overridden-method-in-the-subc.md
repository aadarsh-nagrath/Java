# 110. Can we change the scope of the overridden method in the subclass?

Yes, a subclass can increase the visibility of an overridden method, but it cannot reduce it. Java does not allow an overridden method to have a more restrictive access modifier than the method in the parent class.

- Visibility can be widened (e.g., protected -> public).
- Visibility cannot be narrowed (e.g., public -> protected or private).
- This rule ensures that the overridden method remains accessible wherever the parent method was accessible.

**Example:** A protected method in the parent class can be overridden as public in the child class, but a public method cannot be overridden as protected or private.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#110-can-we-change-the-scope-of-the-overridden-method-in-the-subclass)
