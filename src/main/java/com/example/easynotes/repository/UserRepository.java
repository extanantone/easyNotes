package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    /* NOTA: palabras que aceptan los Named Methods
    * NOTA: Para numeros -          greaterThan, greaterThanEquals, lessThan, lessThanEquals, beetween
    * NOTA: Para strings -          equals, like, notLike, endingWith, startingWith, containing, ignoringCase
    * NOTA: Para booleanos -        true, false
    * NOTA: Para intervalos -       in, notIn
    * NOTA: Para fechas -           before, after
    * NOTA: Operadores logicos -    or, and
    * NOTA: Otras sentencias sql -  distinct, orderBy, sort
    *  */

    //


    //@Query("Select new map(user.firstName as name, size(user.authorNotes) as notes ) from User user where user.lastName like %:lastName%")

    @Query("from User u where u.lastName like %:lastName%")
    List<?> findUserByLastNameLike(@Param("lastName") String lastName);

    @Query( "select distinct user " +
            "from User user " +
            "join user.authorNotes note " +
            "where note.title like %:title%" )
    List<User> findUserByNoteTitleLike(@Param("title") String title);

    @Query( "select distinct user " +
            "from User user " +
            "join user.authorNotes note " +
            "where note.createdAt >= :date" )
    List<User> findUserByNoteCreatedAtLessOrEqualDate(@Param("date") Date date);









    //    // Ejemplo con like
    //    List<User> findUserByLastNameLikeAndFirstNameLike(String lastName, String firstName);
    //
    //    // Ejemplo con diferencia entre like y contains
    //    List<User> findUserByLastNameLikeAndFirstNameContains(String lastName, String firstName);

}
