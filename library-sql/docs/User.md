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
 * Represents a user in the library system.  This entity stores user information such as first name, last name, and email.
 */
@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    /**
     * The unique identifier for the user.  Generated automatically by the database.
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

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Book> books;

    /**
     * Default constructor for JPA.
     */
    public User() {
    }

    /**
     * Constructor to create a new User object.
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

**Summary:**

This Java class `User` represents a user entity for a library system. It stores basic user information like first name, last name, and email, and uses JPA annotations for persistence in a database.  The commented-out `@OneToMany` relationship suggests a potential future extension to track books borrowed by the user.

---
