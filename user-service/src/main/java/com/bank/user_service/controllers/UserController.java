package com.bank.user_service.controllers;

import com.bank.user_service.dto.UserDTO;
import com.bank.user_service.models.User;
import com.bank.user_service.services.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userService;
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> listUsers = userService.getAll();
        if(listUsers != null) return ResponseEntity.ok().body(listUsers);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id).get());
    }


    @PostMapping
    public ResponseEntity<String> addNewUser( UserDTO user) {
        userService.addUser(user);
        return new ResponseEntity<>("Add new user.", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Delete user.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id,
                                             @RequestBody UserDTO user) {
        userService.editUser(user, id);
        return ResponseEntity.ok("Update user.");
    }
}
