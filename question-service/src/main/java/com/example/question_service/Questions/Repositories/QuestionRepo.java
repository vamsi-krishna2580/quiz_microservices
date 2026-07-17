package com.example.question_service.Questions.Repositories;


import com.example.question_service.Questions.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {


    @Query("SELECT q.id" +
            " FROM Question q" +
            " WHERE q.category =:" +
            " category ORDER BY RANDOM()" +
            " LIMIT: numQ ")
    List<Integer> findByRandomByCategory(String category, int numQ);
}

