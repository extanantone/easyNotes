package com.example.easynotes.controller;

import com.example.easynotes.dto.UserRequestDTO;
import com.example.easynotes.dto.UserResponseDTO;
import com.example.easynotes.dto.UserResponseWithCantNotesDTO;
import com.example.easynotes.dto.UserResponseWithNotesDTO;
import com.example.easynotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/notes")
    public List<UserResponseWithNotesDTO> getAllUsersWithNotes() {
        return userService.getAllUsersWithNotes();
    }

    @GetMapping("/users/notes/cant")
    public List<UserResponseWithCantNotesDTO> getAllUsersWithCantNotes() {
        return userService.getAllUsersWithCantNotes();
    }

    @PostMapping("/user")
    public UserResponseDTO createUSer(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUSer(userRequestDTO);
    }

    @GetMapping("/user/{id}")
    public UserResponseDTO getUserById(@PathVariable(value = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/user/{id}/notes")
    public UserResponseWithNotesDTO getUserWithNotesById(@PathVariable(value = "id") Long userId) {
        return userService.getUserWithNotesById(userId);
    }

    @GetMapping("/user/{id}/notes/cant")
    public UserResponseWithCantNotesDTO getUserWithCantNotesById(@PathVariable(value = "id") Long userId) {
        return userService.getUserWithCantNotesById(userId);
    }

    @PutMapping("/user/{id}")
    public UserResponseDTO updateUser(@PathVariable(value = "id") Long userId,
                                     @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userId, userRequestDTO);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        return userService.deleteUser(userId);
    }
}
