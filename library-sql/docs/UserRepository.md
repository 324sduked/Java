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
 * Provides methods for retrieving users by email, ID, and retrieving all users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user to retrieve.
     * @return The user with the matching email, or null if no user is found.
     */
    User findByEmail(String email);

    /**
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    List<User> findAll();

    /**
     * Retrieves a user by their ID.
     * @param user_id The ID of the user to retrieve.
     * @return The user with the matching ID, or null if no user is found.
     */
    User findById(long user_id);
}
```

**Summary:**

This Java interface `UserRepository` acts as a data access layer for managing `User` entities using Spring Data JPA. It inherits from `JpaRepository` and provides methods to find users by email and ID, as well as retrieve all users.

---
