# User Class Documentation

## Overview

This is an auto-generated documentation for class `User`.

## Java Code

```java
package pl.library.library.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Book> books;

    public User() {
    }

    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}

```

## Gemini Response

```java
package pl.library.library.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user in the library system.  This entity holds basic user information.
 *
 * @author [Your Name]
 */
@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    /**
     * The user's first name.
     */
    private String first_name;

    /**
     * The user's last name.
     */
    private String last_name;

    /**
     * The user's email address.
     */
    private String email;


    /**
     * Default constructor for JPA.
     */
    public User() {
    }

    /**
     * Creates a new User object.
     * @param first_name The user's first name.
     * @param last_name The user's last name.
     * @param email The user's email address.
     */
    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
```

**Short Summary:**

This Java class `User` represents a user in a library system. It stores the user's ID, first name, last name, and email address.  It's designed for use with JPA (Jakarta Persistence API) for database interaction.  The commented-out `@OneToMany` relationship suggests potential future expansion to link users with borrowed books.

---
