package com.users.controllers;

import com.users.entities.User;
import com.users.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.add(user);
    }


    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return  userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "User deleted successfully...";
    }

}
