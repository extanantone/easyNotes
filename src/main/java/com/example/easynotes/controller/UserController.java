package com.example.easynotes.controller;

import com.example.easynotes.dto.UserDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository noteRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        modelMapper.map(noteRepository.findAll(), list);
        return list;
    }

    @PostMapping("/users")
    public UserDTO createUSer(@Valid @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(noteRepository.save(user), UserDTO.class);
    }

    @GetMapping("/users/{id}")
    public UserDTO getNoteById(@PathVariable(value = "id") Long userId) {
        User user = noteRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", userId));

        return modelMapper.map(user, UserDTO.class);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody UserDTO userDTO) {
        User user = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", noteId));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return modelMapper.map(noteRepository.save(user), UserDTO.class);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        User note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
