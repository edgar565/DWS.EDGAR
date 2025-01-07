package org.edgar.cookies.controller;

import org.edgar.cookies.entity.User;
import org.edgar.cookies.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public String getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public ResponseEntity<User> createRepair(@RequestBody User user) {
        User userSaved = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }



}