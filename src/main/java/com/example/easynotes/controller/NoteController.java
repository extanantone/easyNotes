package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteRequestDTO;
import com.example.easynotes.dto.NoteResponseWithAuthorDTO;
import com.example.easynotes.model.Note;
import com.example.easynotes.service.NoteService;
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
public class NoteController {

    NoteService noteService;

    @Autowired
    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes/all")
    public List<NoteResponseWithAuthorDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/note")
    public NoteResponseWithAuthorDTO createNote(@Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        return noteService.createNote(noteRequestDTO);
    }

    @GetMapping("/note/{id}")
    public NoteResponseWithAuthorDTO getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/note/{id}")
    public NoteResponseWithAuthorDTO updateNote(@PathVariable(value = "id") Long noteId,
                                                @Valid @RequestBody Note noteDetailsDTO) {
        return noteService.updateNote(noteId, noteDetailsDTO);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        return noteService.deleteNote(noteId);
    }
}