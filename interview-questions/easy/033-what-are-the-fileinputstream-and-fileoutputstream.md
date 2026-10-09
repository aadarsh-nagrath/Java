# 33. What are the FileInputStream and FileOutputStream?

To read and write data, Java offers I/O Streams. A Stream represents an input source or an output destination, which could be a file, an i/o device, another program, etc. [**FileInputStream**](https://www.geeksforgeeks.org/java/java-io-fileinputstream-class-java/) in Java is used to read data from a file as a stream of bytes. It is mostly used for reading binary data such as images, audio files, or serialized objects.

**Example:**

> File file = new File("path_of_the_file");
> FileInputStream inputStream = new FileInputStream(file);

In Java, the [**FileOutputStream**](https://www.geeksforgeeks.org/java/fileoutputstream-in-java/) function is used to write data byte by byte into a given file or file descriptor. Usually, raw byte data, such as pictures, is written into a file using FileOutputStream.

**Example:**

> File file = new File("path_of_the_file");
> FileOutputStream outputStream = new FileOutputStream(file);

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#33-what-are-the-fileinputstream-and-fileoutputstream)
