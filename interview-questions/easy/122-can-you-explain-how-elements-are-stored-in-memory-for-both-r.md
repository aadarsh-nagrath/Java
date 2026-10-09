# 122. Can you explain how elements are stored in memory for both regular arrays and ArrayLists in Java? . Explain.

Both Arrays and ArrayLists store elements in a contiguous memory-like structure, but they differ in how their size is managed. Arrays have a fixed size, whereas ArrayLists use a dynamic internal array that can grow or shrink as needed.

- Arrays store elements in a fixed-size memory block allocated at creation time.
- ArrayLists internally use an array and automatically create a larger array when the current capacity is exceeded.
- ArrayLists provide dynamic resizing, while arrays have a fixed length.

**Example:** An array declared as int[] arr = new int[5] can store only 5 elements. In contrast, an ArrayList automatically increases its capacity when more elements are added beyond its current limit.

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#122-contiguous-memory-locations-are-usually-used-for-storing-actual-values-in-an-array-but-not-in-arraylist-explain)
