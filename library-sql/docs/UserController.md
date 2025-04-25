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
 * REST controller for managing users.  Provides endpoints for adding, retrieving, and searching users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * A simple test endpoint.  Returns 1.  For testing purposes only.
     * @return 1
     */
    @GetMapping("/test")
    public int test(){
        return 1;
    }

    /**
     * Adds a new user.
     * @param user The user object to add.  Must not be null.
     * @return The added user object, including the assigned ID.
     */
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Retrieves all users.
     * @return A list of all users.  May be empty.
     */
    @GetMapping("/all")
    public List<User> GetAll() {
        return userService.GetAllUsers();
    }

    /**
     * Retrieves a user by ID.
     * @param id The ID of the user to retrieve.
     * @return The user with the given ID, or null if not found.
     */
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Retrieves a user by email.
     * @param email The email of the user to retrieve.
     * @return The user with the given email, or null if not found.
     */
    @GetMapping
    public User getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}
```

**Summary:**

This `UserController` class provides a RESTful API for managing users within a library system.  It allows for adding new users, retrieving users by ID or email, and retrieving all users.  A simple test endpoint is also included.

---
