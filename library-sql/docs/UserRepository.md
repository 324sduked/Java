# UserRepository Class Documentation

## Overview

This is an auto-generated documentation for class `UserRepository`.

## Java Code

```java
package pl.library.library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.library.library.entities.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findAll();
    User findById(long user_id);
}

```

## Gemini Response

```java
package pl.library.library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.library.library.entities.User;
import java.util.List;

/**
 * Spring Data JPA repository for managing {@link User} entities.
 * Provides methods for retrieving users by email, ID, and fetching all users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * @param email The email address of the user to find.
     * @return The user with the given email, or null if no such user exists.
     */
    User findByEmail(String email);

    /**
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    List<User> findAll();

    /**
     * Finds a user by their ID.
     * @param user_id The ID of the user to find.
     * @return The user with the given ID, or null if no such user exists.
     */
    User findById(long user_id);
}
```

**Summary:**

This Java interface `UserRepository` acts as a data access layer for managing `User` entities using Spring Data JPA.  It extends `JpaRepository` providing basic CRUD operations and additional methods to find users by email and ID.

---
