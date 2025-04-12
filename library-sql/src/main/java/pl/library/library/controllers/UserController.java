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

