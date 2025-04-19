# UserController Class Documentation

## Overview

This is an auto-generated documentation for class `UserController`.

## Java Code

```java
package pl.library.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.library.library.entities.User;
import pl.library.library.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/test")
    public int test(){
        return 1;
    }
    @PostMapping("/add")
   public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @GetMapping("/all")
    public List<User> GetAll() {
        return userService.GetAllUsers();
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @GetMapping
    public User getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}


```

## Gemini Response

```java
package pl.library.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.library.library.entities.User;
import pl.library.library.services.UserService;

import java.util.List;

/**
 * REST controller for managing users.  Provides endpoints for adding users, retrieving users by ID or email, and retrieving all users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * A test endpoint, returning 1.  For testing purposes only.
     * @return 1 (integer)
     */
    @GetMapping("/test")
    public int test(){
        return 1;
    }

    /**
     * Adds a new user.
     * @param user The user object to be added.
     * @return The added user object, including the generated ID.
     */
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Retrieves all users.
     * @return A list of all users.
     */
    @GetMapping("/all")
    public List<User> GetAll() {
        return userService.GetAllUsers();
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The user with the given ID, or null if not found.
     */
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user to retrieve.
     * @return The user with the given email address, or null if not found.
     */
    @GetMapping
    public User getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}
```

**Summary:**

This `UserController` class provides a RESTful API for managing users within a library application.  It allows for adding new users, retrieving users by ID or email, and retrieving all users.  A test endpoint is included for debugging purposes.

---
