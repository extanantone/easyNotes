package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteDTO;
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
import java.util.Date;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/notes")
    public List<NoteDTO> getAllNotes() {
        List<NoteDTO> list = new ArrayList<>();
        modelMapper.map(noteRepository.findAll(), list);
        return list;
    }

    @PostMapping("/notes")
    public NoteDTO createNote(@Valid @RequestBody NoteDTO noteDTO) {
        Note note = modelMapper.map(noteDTO, Note.class);

        //!FIXME
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());

        Note noteReq = noteRepository.save(note);
        return modelMapper.map(noteReq, NoteDTO.class);
    }

    @GetMapping("/notes/{id}")
    public NoteDTO getNoteById(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        return modelMapper.map(note, NoteDTO.class);
    }

    @PutMapping("/notes/{id}")
    public NoteDTO updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetailsDTO) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetailsDTO.getTitle());
        note.setContent(noteDetailsDTO.getContent());
        note.setAuthor(noteDetailsDTO.getAuthor());

        Note updatedNote = noteRepository.save(note);
        return modelMapper.map(updatedNote, NoteDTO.class);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}


