package com.example.easynotes.service;

import com.example.easynotes.dto.UserRequestDTO;
import com.example.easynotes.dto.UserResponseDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.ThankRepository;
import com.example.easynotes.repository.UserRepository;
import com.example.easynotes.utils.ListMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);

    NoteRepository noteRepository = Mockito.mock(NoteRepository.class);

    ThankRepository thankRepository = Mockito.mock(ThankRepository.class);

    ModelMapper modelMapper = new ModelMapper();

    UserService userService = new UserService(userRepository, noteRepository, thankRepository, modelMapper,new ListMapper(modelMapper));

    @Test
    void getAllUsers() {
        userService.getAllUsers();
    }

    @Test
    void getAllUsersWithNotes() {
        userService.getAllUsersWithNotes();
    }

    @Test
    void getAllUsersWithCantNotes() {
        userService.getAllUsersWithCantNotes();
    }

    @Test
    void createUSerOk() {
        when(userRepository.save(any(User.class))).thenReturn(new User());
        Assertions.assertDoesNotThrow(
                () -> userService.createUSer(new UserRequestDTO()) );
    }

    @Test
    void getUserById() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L) );
    }

    @Test
    void getUserByIdOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Assertions.assertDoesNotThrow(
                () -> userService.getUserById(1L) );
    }

    @Test
    void getUserWithNotesById() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserWithNotesById(1L) );
    }

    @Test
    void getUserWithNotesByIdOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Assertions.assertDoesNotThrow(
                () -> userService.getUserWithNotesById(1L) );
    }

    @Test
    void getUserWithCantNotesById() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserWithCantNotesById(1L) );
    }
    @Test
    void getUserWithCantNotesByIdOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Assertions.assertDoesNotThrow(
                () -> userService.getUserWithCantNotesById(1L) );
    }

    @Test
    void updateUser() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.updateUser(1L, new UserRequestDTO()) );
    }

    @Test
    void updateUserOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(new User() );
        Assertions.assertDoesNotThrow(
                () -> userService.updateUser(1L, new UserRequestDTO()) );
    }


    @Test
    void deleteUser() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.deleteUser(1L) );
    }

    @Test
    void deleteUserOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Assertions.assertDoesNotThrow(
                () -> userService.deleteUser(1L) );
    }

    @Test
    void getUsersLastNameLike() {
        userService.getUsersLastNameLike("argento");
    }

    @Test
    void getUsersByNoteTitleLike() {
        userService.getUsersByNoteTitleLike("Me va a enfermar!");
    }

    @Test
    void getUsersByNoteCreatedAfterDate() {
        userService.getUsersByNoteCreatedAfterDate(new Date());
    }

    @Test
    void createThank() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.createThank(1L, 2L) );
    }

    @Test
    void createThankOk() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(noteRepository.findById(2L)).thenReturn(Optional.of(new Note()));

        Assertions.assertDoesNotThrow(
                () -> userService.createThank(1L, 2L) );
    }

    @Test
    void testGetUserById() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L) );
    }



}