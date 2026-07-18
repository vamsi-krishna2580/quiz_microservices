package com.example.quiz_service.Quizz.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizDto {
    @NotBlank
    String categoryName;
    @NotNull
    Integer numQuestions;
    @NotBlank
    String title;
}
