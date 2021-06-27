package com.example.easynotes.service;

import com.example.easynotes.dto.NoteRequestDTO;
import com.example.easynotes.dto.NoteResponseWithAuthorDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.UserRepository;
import com.example.easynotes.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@Service
public class NoteService {

    NoteRepository noteRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;

    @Autowired
    NoteService(NoteRepository noteRepository,
                UserRepository userRepository,
                ModelMapper modelMapper,
                ListMapper listMapper) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.listMapper = listMapper;

        // Sikpping map Note author from NoteRequestDTO (this class only have author_id)
        modelMapper.addMappings(new PropertyMap<NoteRequestDTO, Note>() {
            @Override
            protected void configure() {
                skip(destination.getAuthor());
            }
        });

        this.modelMapper = modelMapper;
    }

    public List<NoteResponseWithAuthorDTO> getAllNotes() {
        List<Note> note = noteRepository.findAll();
        return listMapper.mapList(note, NoteResponseWithAuthorDTO.class);
    }

    public NoteResponseWithAuthorDTO createNote(NoteRequestDTO noteRequestDTO) {
        // Create new note
        Note note = modelMapper.map(noteRequestDTO, Note.class);
        // Find user with id from noteDTO
        User user = userRepository.findById(noteRequestDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", noteRequestDTO.getAuthorId()));
        // Set author to the new note
        note.setAuthor(user);

        //!FIXME
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());

        Note noteReq = noteRepository.save(note);
        return modelMapper.map(noteReq, NoteResponseWithAuthorDTO.class);
    }

    public NoteResponseWithAuthorDTO getNoteById(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        return modelMapper.map(note, NoteResponseWithAuthorDTO.class);
    }

    public NoteResponseWithAuthorDTO updateNote(Long noteId,
                                                Note noteDetailsDTO) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetailsDTO.getTitle());
        note.setContent(noteDetailsDTO.getContent());
        note.setAuthor(noteDetailsDTO.getAuthor());

        Note updatedNote = noteRepository.save(note);
        return modelMapper.map(updatedNote, NoteResponseWithAuthorDTO.class);
    }

    public ResponseEntity<?> deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}


