package com.example.easynotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class UserCategoryDTO {

  public enum cat {PublicadorDiario,PublicadorSemanal,Publicador};

    private Long id;
    private String category;
}
