# 58. What do you understand by the jagged array?

A jagged array is an array of arrays in which each row can have a different number of elements. Unlike a regular multidimensional array, all rows are not required to have the same size.

- It helps save memory when different rows require different amounts of storage.
- In Java, a two-dimensional array is internally implemented as an array of arrays, making jagged arrays possible.

**Syntax:**

> int[][] Arr = new int[][] {
>  {1, 2, 8},
>  {7, 5},
>  {6, 7, 2, 6}
> };

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#58-what-do-you-understand-by-the-jagged-array)
