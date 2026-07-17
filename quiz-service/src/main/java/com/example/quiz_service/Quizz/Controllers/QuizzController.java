package com.example.quiz_service.Quizz.Controllers;

import com.example.quiz_service.Quizz.Models.QuizDto;
import com.example.quiz_service.Quizz.Models.Response;
import com.example.quiz_service.Quizz.Services.QuizzService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizz")
public class QuizzController {

    private final QuizzService quizzService;
    public QuizzController(QuizzService quizzService){
        this.quizzService = quizzService;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getQuizz (@PathVariable Integer id){
        return quizzService.getQuizz(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuizz (@RequestBody QuizDto quizDto){
        return quizzService.createQuizz(quizDto.getTitle(), quizDto.getCategoryName(), quizDto.getNumQuestions());
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizzService.caluclateResult(id, responses);
    }
}
