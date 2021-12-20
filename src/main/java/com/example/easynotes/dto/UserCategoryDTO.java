package com.example.easynotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
public class UserCategoryDTO {

  public enum cat {PublicadorDioario,PublicadorSemanal,Publicador};

    private Long id;
    private String category;
}
