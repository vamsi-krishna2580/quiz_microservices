package com.example.question_service.Questions.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Option1 cannot be empty")
    private String option1;
    @NotBlank(message = "Option2 cannot be empty")
    private String option2;
    @NotBlank(message = "Option3 cannot be empty")
    private String option3;
    @NotBlank(message = "Option4 cannot be empty")
    private String option4;
    @NotBlank(message = "Category cannot be empty")
    private String category;
    @NotBlank(message = "RightAnswer cannot be empty")
    private String rightAnswer;

}
