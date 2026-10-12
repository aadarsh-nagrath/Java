# 221. Explain and implement the Factory Design Pattern with modifications.

**Factory pattern**: a creational pattern where object creation is delegated to a factory, so clients depend on an **interface** and never call `new ConcreteClass()` themselves.

### 1. Simple factory (the textbook version)
```java
interface Notification { void send(String to, String msg); }
class EmailNotification implements Notification { public void send(String to, String m) { /* SMTP */ } }
class SmsNotification   implements Notification { public void send(String to, String m) { /* Twilio */ } }

class NotificationFactory {
    static Notification create(String type) {
        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS"   -> new SmsNotification();
            default      -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

NotificationFactory.create("email").send("a@b.com", "Hi");
```
**Problem:** adding `PushNotification` means **editing** the switch, which violates Open/Closed. Strings are also not type-safe.

### 2. Modification: enum key + registry (Open/Closed)
```java
enum Channel { EMAIL, SMS, PUSH }

class NotificationFactory {
    private static final Map<Channel, Supplier<Notification>> REGISTRY = new EnumMap<>(Channel.class);

    static {
        register(Channel.EMAIL, EmailNotification::new);
        register(Channel.SMS,   SmsNotification::new);
    }

    static void register(Channel c, Supplier<Notification> s) { REGISTRY.put(c, s); }

    static Notification create(Channel c) {
        Supplier<Notification> s = REGISTRY.get(c);
        if (s == null) throw new IllegalArgumentException("No notification for " + c);
        return s.get();
    }
}

NotificationFactory.register(Channel.PUSH, PushNotification::new);   // extend without editing the factory
```

### 3. Modification: Spring-managed factory (what you'd do in a real project)
Let Spring find every implementation. A new channel is just a new `@Component`.
```java
interface Notification { Channel channel(); void send(String to, String msg); }

@Component class EmailNotification implements Notification { public Channel channel() { return Channel.EMAIL; } ... }
@Component class SmsNotification   implements Notification { public Channel channel() { return Channel.SMS; } ... }

@Component
class NotificationFactory {
    private final Map<Channel, Notification> byChannel;

    NotificationFactory(List<Notification> all) {           // Spring injects every implementation
        this.byChannel = all.stream().collect(Collectors.toMap(Notification::channel, n -> n));
    }

    Notification get(Channel c) {
        return Optional.ofNullable(byChannel.get(c))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported " + c));
    }
}
```

**Related patterns to mention:** *Factory Method* (a subclass decides which class to instantiate via an overridable `createX()` method), *Abstract Factory* (families of related objects, e.g. `UiFactory` → `WindowsButton` + `WindowsCheckbox`). JDK examples: `Calendar.getInstance()`, `List.of()`, `NumberFormat.getInstance()`, `Executors.newFixedThreadPool()`.

---

[← Back to Medium index](README.md)
