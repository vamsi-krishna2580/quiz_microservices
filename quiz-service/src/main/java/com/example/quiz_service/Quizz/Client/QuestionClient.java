package com.example.quiz_service.Quizz.Client;

import com.example.quiz_service.Quizz.Models.QuestionDto;
import com.example.quiz_service.Quizz.Models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/question")
public interface QuestionClient {

    @GetExchange("question/generate")
    ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ);

    @PostExchange("question/getQuestions")
    ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostExchange("question/getScore")
    ResponseEntity<Integer> getScore (@RequestBody List<Response> responses);
}
