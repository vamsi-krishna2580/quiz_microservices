package com.example.quiz_service.Quizz.Repositories;


import com.example.quiz_service.Quizz.Models.Question;
import com.example.quiz_service.Quizz.Models.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzRepo extends JpaRepository<Quizz, Integer> {

    List<Question> findByCategoryRandom(@Param("numQ") int numQ, @Param("category") String category);
}
