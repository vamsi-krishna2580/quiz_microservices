package com.example.question_service.Questions.Repositories;


import com.example.question_service.Questions.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {


    @Query(value = """
            SELECT id 
            FROM question 
            WHERE category =:category 
            ORDER BY RAND() 
            LIMIT :numQ
            """, nativeQuery = true)
    List<Integer> findByRandomByCategory(
            @Param("category") String category,
            @Param("numQ") int numQ);
}

