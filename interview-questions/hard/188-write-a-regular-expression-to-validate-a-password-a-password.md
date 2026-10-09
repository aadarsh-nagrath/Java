# 188. Write a regular expression to validate a password. A password must start with an alphabet and followed by alphanumeric characters; Its length must be in between 8 to 20.

> regex = “^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$”

**Explanation:**

- ^ used for starting character of the string.
- (?=.*[0-9]) used for a digit must occur at least once.
- (?=.*[a-z]) used for a lowercase alphabet must occur at least once.
- (?=.*[A-Z]) used for an upper case alphabet that must occur at least once in the substring.
- (?=.*[@#$%^&-+=()] used for a special character that must occur at least once.
- (?=\\S+$) white spaces don’t allow in the entire string.
- .{8, 20} used for at least 8 characters and at most 20 characters.
- $ used for the end of the string.

---

[← Back to Hard index](README.md) · Source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/java-interview-questions/#188-write-a-regular-expression-to-validate-a-password-a-password-must-start-with-an-alphabet-and-followed-by-alphanumeric-characters-its-length-must-be-in-between-8-to-20)
