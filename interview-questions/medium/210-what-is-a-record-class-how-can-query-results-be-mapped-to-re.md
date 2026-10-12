# 210. What is a Record class? How can query results be mapped to Records, and how do you fetch the first record?

A **record** (Java 16) is a concise, **immutable data carrier**:

```java
public record UserDto(Long id, String name, String email) {
    public UserDto {                                   // compact constructor → validation
        if (email == null) throw new IllegalArgumentException("email");
    }
}
```

The compiler generates: `private final` fields, a canonical constructor, accessors `id()` / `name()` (no `get` prefix), `equals()`, `hashCode()` and `toString()`. Records are implicitly `final`, cannot extend another class (they extend `java.lang.Record`), and can implement interfaces and have static fields/methods.

**Records are not JPA entities.** JPA needs a no-arg constructor, mutable state and non-final classes for proxies. Use records as **DTOs / projections**.

### Mapping query results to records

**1. Spring Data JPA, JPQL constructor expression**
```java
@Query("select new com.app.dto.UserDto(u.id, u.name, u.email) from User u where u.active = true")
List<UserDto> findActiveUsers();
```

**2. Spring Data JPA, class-based (DTO) projection.** The record's component names must match entity properties:
```java
List<UserDto> findByActiveTrue();
```

**3. `JdbcTemplate` / `JdbcClient`**
```java
List<UserDto> users = jdbcTemplate.query(
    "select id, name, email from users",
    (rs, i) -> new UserDto(rs.getLong("id"), rs.getString("name"), rs.getString("email")));

// or let Spring map columns → record components (Spring 5.3+)
jdbcTemplate.query(sql, new DataClassRowMapper<>(UserDto.class));

// Spring 6.1 JdbcClient
jdbcClient.sql(sql).query(UserDto.class).list();
```

### Fetching the first record

```java
// Spring Data derived query → adds LIMIT 1
Optional<UserDto> findFirstByOrderByCreatedAtDesc();
Optional<User> findTopByStatusOrderByIdAsc(String status);

// JPQL / Criteria
em.createQuery(jpql, UserDto.class).setMaxResults(1).getResultStream().findFirst();

// JdbcClient
jdbcClient.sql("select ... order by created_at desc limit 1").query(UserDto.class).optional();
```

> Avoid `findAll().get(0)`, which loads the whole table. Also avoid `queryForObject` when 0 rows is possible: it throws `EmptyResultDataAccessException` (and `IncorrectResultSizeDataAccessException` for >1 row).

---

[← Back to Medium index](README.md)
