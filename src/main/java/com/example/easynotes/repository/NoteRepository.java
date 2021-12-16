package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // NOTA: TOP 3 DE NOTAS CON MAS LIKES SEGUN AÑO

//    @Query("FROM Note n " +
//            "WHERE YEAR(n.createdAt) = :year " +
//            "ORDER BY SIZE(n.thanks) ASC" )
    /*@Query("FROM Thank t " +
            "WHERE YEAR(t.createdAt) = :year " +
            "ORDER BY SIZE(n.thanks) ASC" )*/
    @Query("SELECT new map(note.id as id, COUNT(note.id) as cant_thanks ) " +
            "from Note note inner join note.thanks as thank " +
            "where YEAR(thank.createdAt) = :year " +
            "group by note.id order by cant_thanks desc")
    List<HashMap<String, Object>> findTopThreeNotesMostThankedByDate(int year);
}
