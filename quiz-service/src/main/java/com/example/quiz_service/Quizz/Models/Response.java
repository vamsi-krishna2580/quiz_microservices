package com.example.quiz_service.Quizz.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Response {

    @NotNull(message =  "Question id is required")
    private Integer id;
    @NotBlank(message = "Response cannot be empty")
    private String response;
}
