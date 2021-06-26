package com.example.easynotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private Long id;

    @NotNull
    @Valid
    private UserDTO author;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
