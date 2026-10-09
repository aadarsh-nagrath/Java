# 119. How can you synchronize an ArrayList in Java?

An ArrayList can be synchronized using two methods mentioned below:

- Using Collections.synchronizedList()
- Using CopyOnWriteArrayList

Using Collections.synchronizedList():

> public static List\<T> synchronizedList(List\<T> list)

Using CopyOnWriteArrayList:

- Create an empty List.
- It implements the List interface
- It is a thread-safe variant of ArrayList
- T represents generic

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#119-how-can-you-synchronize-an-arraylist-in-java)
