# 66. What is the difference between static (class) method and instance method?

| Static(Class) method | Instance method |
| --- | --- |
| Static method is associated with a class rather than an object. | The instance method is associated with an object rather than a class. |
| Static methods can be called using the class name only without creating an instance of a class. | The instance methods can be called on a specific instance of a class using the object reference. |
| Static methods do not have access to this keyword. | Instance methods have access to this keyword. |
| Static methods can access only static members of the class. | Instance methods can access both static and non-static methods of the class. |
| Static methods cannot be overridden because they are resolved at compile time, not at run time. This means that the compiler decides which method to call based on the reference type, not on the object type. | Instance methods can be overridden because they are resolved at run time, not at compile time. This means that the compiler decides which method to call based on the object type, not on the reference type. |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#66-what-is-the-difference-between-static-class-method-and-instance-method)
