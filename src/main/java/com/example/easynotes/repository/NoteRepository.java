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
/*
note    thank
id      note_id

2, h2,  2, 3, 2021 v  *2
2, h2,  2, 9, 2021 v  *2
2, h2,  2,11, 2019 x

1, h1,  1, 3, 2011 x
1, h1,  1, 5, 2021 v  *1
1, h1,  1, 7, 2021 v  *1
1, h1,  1,11, 2021 v  *1
1, h1,  1, 9, 2019 x

3, h3,  3, 9, 2021 v  *3

        id           cant_thanks
        2            2
        1            3
        3            1

        id           cant_thanks
        1            3
        2            2
        3            1
*/
