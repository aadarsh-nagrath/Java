# 213. What is exception handling? What is `@ControllerAdvice`?

**Exception handling** is the mechanism for dealing with runtime errors so the program can recover or fail gracefully instead of crashing. It uses `try`, `catch`, `finally`, `throw`, `throws` and try-with-resources (see [152](152-what-is-exception-handling.md)).

**`@ControllerAdvice`** (Spring MVC) is a **global, cross-cutting exception handler** for all controllers. Instead of a `try/catch` in every endpoint, you throw domain exceptions and handle them in one place. `@RestControllerAdvice` = `@ControllerAdvice` + `@ResponseBody`.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleNotFound(UserNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());   // RFC 7807 (Spring 6)
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)                 // last-resort fallback
    public ProblemDetail handleAll(Exception ex) {
        log.error("Unhandled", ex);
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);   // don't leak stack traces
    }
}
```

**Key points**
- The **most specific** `@ExceptionHandler` wins.
- A local `@ExceptionHandler` inside a controller takes priority over the global advice.
- Scope it with `@ControllerAdvice(basePackages = "com.app.api")` or `assignableTypes = ...`.
- Alternatives: `@ResponseStatus` on the exception class, or throwing `ResponseStatusException`.
- It only covers exceptions thrown **inside the DispatcherServlet**. Exceptions from servlet filters (e.g. Spring Security filters) need an `AuthenticationEntryPoint` / `AccessDeniedHandler`.

---

[← Back to Medium index](README.md)
