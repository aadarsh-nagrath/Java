# 235. Difference between Path Variables and Request Parameters.

```
GET /users/42/orders?status=PAID&page=2
           └┬┘             └────┬────────┘
     path variable      request (query) parameters
```

```java
@GetMapping("/users/{userId}/orders")
public List<Order> orders(
        @PathVariable Long userId,
        @RequestParam(required = false) OrderStatus status,
        @RequestParam(defaultValue = "0") int page) { ... }
```

| | `@PathVariable` | `@RequestParam` |
| --- | --- | --- |
| Location | Part of the URI **path** `/users/{id}` | **Query string** `?key=value` (or form data) |
| Purpose | **Identifies a resource** | **Filters, sorts, paginates or modifies** the result |
| Required | Yes by default (the URL won't match without it) | Yes by default; use `required = false` / `defaultValue` |
| Optional values | Awkward (needs multiple mappings or `Optional`) | Natural |
| Multiple values | No | `?tag=a&tag=b` → `List<String> tag` |
| REST example | `GET /products/101` | `GET /products?category=books&sort=price` |
| Caching / SEO | Clean, cacheable URLs | Fine, but less "resource-like" |

**Rule of thumb:** *identity → path variable; criteria → request parameter.* `@RequestParam Map<String,String>` captures all params. If the method parameter name differs, use `@PathVariable("id")` or compile with `-parameters` (Spring Boot does this by default).

---

[← Back to Easy index](README.md)
