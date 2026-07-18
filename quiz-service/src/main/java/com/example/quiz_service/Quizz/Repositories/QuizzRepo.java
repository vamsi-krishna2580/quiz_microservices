package com.example.quiz_service.Quizz.Repositories;


import com.example.quiz_service.Quizz.Models.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizzRepo extends JpaRepository<Quizz, Integer> {
}
