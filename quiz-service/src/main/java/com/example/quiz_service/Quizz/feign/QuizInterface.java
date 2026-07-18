package com.example.quiz_service.Quizz.feign;

import com.example.quiz_service.Quizz.Models.QuestionDto;
import com.example.quiz_service.Quizz.Models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("question/getQuestions")
    ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    ResponseEntity<Integer> getScore (@RequestBody List<Response> responses);

}
