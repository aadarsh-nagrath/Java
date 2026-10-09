# 31. What is the Difference Between the Reader/Writer and InputStream/OutputStream Class Hierarchies?

Both hierarchies are used for I/O operations in Java, but they handle different types of data. The InputStream and OutputStream classes work with binary data, whereas the Reader and Writer classes work with character data.

| Reader/Writer | InputStream/OutputStream |
| --- | --- |
| Used for character-based data. | Used for byte-based (binary) data. |
| Handles Unicode characters automatically. | Handles raw bytes without character conversion. |
| Suitable for text files and text processing. | Suitable for images, audio, videos, and binary files. |
| Main classes are Reader and Writer. | Main classes are InputStream and OutputStream. |
| **Examples:** FileReader, BufferedReader, FileWriter. | **Examples:** FileInputStream, BufferedInputStream, FileOutputStream. |

---

[← Back to Easy index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#31-what-is-the-difference-between-the-readerwriter-class-hierarchy-and-the-inputstreamoutputstream-class-hierarchy)
