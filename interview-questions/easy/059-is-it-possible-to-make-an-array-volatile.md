# 59. Is it possible to make an array volatile?

Yes, an array reference can be declared as volatile in Java. However, the [volatile keyword](https://www.geeksforgeeks.org/java/volatile-keyword-in-java/) applies only to the array reference, not to the individual elements of the array.

- When an array is declared volatile, changes to the reference are immediately visible to all threads.
- Modifying an element of the array does not make that change automatically thread-safe or visible through volatile.
- For thread-safe updates to array elements, additional synchronization mechanisms may be required.

**Example:**

> volatile int[] numbers = new int[5];

Here, numbers is a volatile reference to an array, but the elements numbers[0], numbers[1], etc., are not volatile.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#59-is-it-possible-to-make-an-array-volatile)
