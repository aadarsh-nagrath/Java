# 57. How to copy an array in Java?

In Java, there are multiple ways to copy an array based on the requirements:

**1. clone() Method (Shallow Copy)**

- The clone() method creates a new array with its own memory but contains references to the same objects for non-primitive data types.
- For primitive arrays, it behaves like a deep copy since primitive values are directly copied.

> int[] arr = {1, 2, 3, 5, 0};
> int[] tempArr = arr.clone(); // Creates a new array with copied values

**2. System.arraycopy() Method (Deep Copy)**

- This method creates a new array and copies elements from the original array.
- It allows partial copying by specifying an offset and length.

> int[] arr = {1, 2, 7, 9, 8};
> int[] tempArr = new int[arr.length];
> System.arraycopy(arr, 0, tempArr, 0, arr.length);

**3. Arrays.copyOf() Method (Creates New Array)**

- This method creates a new array and copies the entire original array into it.
- If the specified length is greater than the original array, extra elements are initialized with default values.

> int[] arr = {1, 2, 4, 8};
> int[] tempArr = Arrays.copyOf(arr, arr.length);

**4. Arrays.copyOfRange() Method (Copying a Subset)**

- Similar to copyOf(), but allows copying a specific range of elements.

> int[] arr = {1, 2, 4, 8};
> int[] tempArr = Arrays.copyOfRange(arr, 0, arr.length);

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#57-how-to-copy-an-array-in-java)
