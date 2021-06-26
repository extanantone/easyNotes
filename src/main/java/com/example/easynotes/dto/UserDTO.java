package com.example.easynotes.dto;

import com.example.easynotes.model.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private Long id;

        @NotEmpty
        private String firstName;

        @NotEmpty
        private String lastName;

        @Valid
        private Set<Note> authorNotes;
}
