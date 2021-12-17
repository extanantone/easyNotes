package com.example.easynotes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NoteResponseWithTypeOfLikes extends NoteResponseWithCantLikesDTO{

    String typeNote;



    public NoteResponseWithTypeOfLikes(Long id, int cantThanks, String typeNote) {
        super(id, cantThanks);
        this.typeNote = typeNote;
    }
}
