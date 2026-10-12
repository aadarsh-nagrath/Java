# 214. Reverse a string without using predefined methods.

Interviewers want to see that you can do it without `StringBuilder.reverse()`. **Two-pointer swap** on a char array is O(n) time and O(n) space (strings are immutable, so a copy is unavoidable):

```java
static String reverse(String s) {
    char[] c = s.toCharArray();
    for (int i = 0, j = c.length - 1; i < j; i++, j--) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
    return new String(c);
}
```

If even `toCharArray()` is off-limits, build a new array with `charAt` and `length()` (you can't read a `String` without *some* method):

```java
static String reverse(String s) {
    char[] out = new char[s.length()];
    for (int i = 0; i < out.length; i++) out[i] = s.charAt(out.length - 1 - i);
    return new String(out);
}
```

Recursive version (elegant but O(n²) because of substring copies, and it can overflow the stack on long input):

```java
static String reverse(String s) {
    return s.isEmpty() ? s : reverse(s.substring(1)) + s.charAt(0);
}
```

**Avoid** `result = result + c` in a loop. Each `+` creates a new String, which makes it O(n²).

**Follow-up worth mentioning:** reversing `char`s breaks **surrogate pairs** (emoji like 😀 are two `char`s). `StringBuilder.reverse()` handles that case correctly. Also handle `null` (throw or return `null`, depending on the contract).

---

[← Back to Easy index](README.md)
