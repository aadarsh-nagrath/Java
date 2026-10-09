# 124. How does the size of ArrayList grow dynamically? And also state how it is implemented internally.

An ArrayList grows dynamically by creating a new larger array when its current capacity becomes full. It then copies all existing elements from the old array to the new array, allowing additional elements to be stored.

- Internally, ArrayList is implemented using a resizable array.
- When the array is full, a new array with a larger capacity is created and existing elements are copied into it.
- This resizing process is automatic and transparent to the programmer.

**Example:** If an ArrayList reaches its capacity and a new element is added, Java automatically allocates a larger internal array and transfers all existing elements to it before inserting the new element.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#124-how-does-the-size-of-arraylist-grow-dynamically-and-also-state-how-it-is-implemented-internally)
