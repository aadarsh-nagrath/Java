# 204. Differentiate between HashMap and TreeMap.

| HashMap | TreeMap |
| --- | --- |
| Hasmap uses a hashtable in order to store key-value pairs. | Treemap uses Red-black trees to store key-value pair. |
| Hashmap does not maintain any specific order for key-value pairs. | Treemap maintains a natural ordering based on the keys. |
| Order of iteration is not guaranteed in the hashmap. | Iteration is of sorted order based on keys. |
| Hashmaps are faster for retrieval compared to Treemap. | Retrieval in Treemap is slower as it uses tree traversal to find keys. |
| Hashmap is implemented by using an Array of linked list. | TreeMap is implemented using a Red-black Tree. |
| Hashmap uses the equals() method of the Object class to compare keys. | TreeMap uses compareTo() method to compare keys. |

---

[← Back to Medium index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#204-differentiate-between-hashmap-and-treemap)
