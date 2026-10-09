# 27. Explain the difference between instance variable and a class variable.

Both instance variables and class variables are declared inside a class, but they differ in ownership and memory allocation. An instance variable belongs to an object, whereas a class variable belongs to the class itself.

| Instance Variable | Class Variable |
| --- | --- |
| Declared without the static keyword. | Declared using the static keyword. |
| Each object gets its own separate copy. | A single copy is shared by all objects. |
| Memory is allocated when an object is created. | Memory is allocated once when the class is loaded. |
| Accessed using an object reference. | Can be accessed using the class name. |
| Changes affect only that particular object. | Changes are reflected across all objects. |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#27-explain-the-difference-between-instance-variable-and-a-class-variable)
