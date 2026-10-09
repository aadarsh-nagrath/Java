# 149. What is the difference between Comparable and Comparator?

| Comparable | Comparator |
| --- | --- |
| The interface is present in java.lang package. | The Interface is present in java.util package. |
| Provides compareTo() method to sort elements. | Provides compare() method to sort elements. |
| It provides single sorting sequences. | It provides multiple sorting sequences. |
| The logic of sorting must be in the same class whose object you are going to sort. | The logic of sorting should be in a separate class to write different sorting based on different attributes of objects. |
| Method sorts the data according to fixed sorting order. | Method sorts the data according to the customized sorting order. |
| It affects the original class. | It doesn't affect the original class. |
| Implemented frequently in the API by Calendar, Wrapper classes, Date, and String. | It is implemented to sort instances of third-party classes. |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#149-what-is-the-difference-between-comparable-and-comparator)
