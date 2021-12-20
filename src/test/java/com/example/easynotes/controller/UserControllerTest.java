package com.example.easynotes.controller;

import com.example.easynotes.dto.UserRequestDTO;
import com.example.easynotes.service.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

class UserControllerTest {


    IUserService userService = Mockito.mock(IUserService.class);

    UserController userController = new UserController(userService);

    @Test
    void getAllUsers() {
        userController.getAllUsers();
    }

    @Test
    void getAllUsersWithNotes() {
        userController.getAllUsersWithNotes();
    }

    @Test
    void getAllUsersWithCantNotes() {
        userController.getAllUsersWithCantNotes();
    }

    @Test
    void createUSer() {
        userController.createUSer(new UserRequestDTO());
    }

    @Test
    void getUserById() {
        userController.getUserById(1L);
    }

    @Test
    void getUserWithNotesById() {
        userController.getUserWithNotesById(1L);
    }

    @Test
    void getUsersLastNameLike() {
        userController.getUsersLastNameLike("pepe");
    }

    @Test
    void fetchResult() {
        userController.fetchResult(new Date());
    }

    @Test
    void testFetchResult() {
        userController.fetchResult(new Date());
    }

    @Test
    void createGreat() {
        userController.createGreat(1L, 2L);
    }

    @Test
    void getUserWithCantNotesById() {
        userController.getUserWithCantNotesById(1L);
    }

    @Test
    void updateUser() {
        userController.updateUser(1L,new UserRequestDTO());
    }

    @Test
    void deleteUser() {
        userController.deleteUser(1L);
    }
}