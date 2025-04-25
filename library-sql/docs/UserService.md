# UserService Class Documentation

## Overview

This is an auto-generated documentation for class `UserService`.

## Java Code

```java
package pl.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.library.library.entities.User;
import pl.library.library.repositories.UserRepository;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findById(Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

}

```

## Gemini Response

```java
package pl.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.library.library.entities.User;
import pl.library.library.repositories.UserRepository;
import java.util.List;

/**
 * Service class for managing users in the library system.
 * Provides methods for adding, retrieving, and searching users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user to the system.
     * @param user The user object to be added.
     * @return The saved user object, including the generated ID.  Returns null if save fails.
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a list of all users in the system.
     * @return A list of all User objects. Returns an empty list if no users are found.
     */
    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user to be retrieved.
     * @return The User object with the matching email. Returns null if no user is found.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves a user by their ID.
     * @param user_id The ID of the user to be retrieved.
     * @return The User object with the matching ID. Returns null if no user is found.
     */
    public User findById(Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

}
```

**Summary:**

The `UserService` class provides a service layer for managing user data within the library application. It offers methods to add new users, retrieve all users, find users by email, and find users by ID, interacting with the underlying `UserRepository` for data persistence.

---
