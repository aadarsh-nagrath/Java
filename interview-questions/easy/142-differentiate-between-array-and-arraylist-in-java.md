# 142. Differentiate between Array and ArrayList in Java.

| Array | ArrayList |
| --- | --- |
| Single-dimensional or multidimensional | Single-dimensional |
| For and for each used for iteration | Here iterator is used to traverse riverArrayList |
| length keyword returns the size of the array. | size() method is used to compute the size of ArrayList. |
| The array has Fixed-size. | ArrayList size is dynamic and can be increased or decreased in size when required. |
| It is faster as above we see it of fixed size | It is relatively slower because of its dynamic nature |
| Primitive data types can be stored directly in unlikely objects. | Primitive data types are not directly added to unlikely arrays, they are added indirectly with help of autoboxing and unboxing |
| They can not be added here hence the type is in the unsafe. | They can be added here hence makingArrayList type-safe. |
| The assignment operator only serves the purpose | Here a special method is used known as add() method |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#142-differentiate-between-array-and-arraylist-in-java)
