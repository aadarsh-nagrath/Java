# 146. What is the difference between Iterator and ListIterator?

| Iterator | ListIterator |
| --- | --- |
| Can traverse elements present in Collection only in the forward direction. | Can traverse elements present in Collection both in forward and backward directions. |
| Used to traverse Map, List, and Set. | Can only traverse List and not the other two. |
| Indexes can't be obtained using Iterator | It has methods like nextIndex() and previousIndex() to obtain indexes of elements at any time while traversing the List. |
| Can't modify or replace elements present in Collection | Can modify or replace elements with the help of set(E e) |
| Can't add elements, and also throws ConcurrentModificationException. | Can easily add elements to a collection at any time. |
| Certain methods of Iterator are next(), remove(), and hasNext(). | Certain methods of ListIterator are next(), previous(), hasNext(), hasPrevious(), add(E e). |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#146-what-is-the-difference-between-iterator-and-listiterator)
