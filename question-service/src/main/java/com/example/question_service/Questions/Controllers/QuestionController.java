package com.example.question_service.Questions.Controllers;

import com.example.question_service.Questions.Models.Question;
import com.example.question_service.Questions.Models.QuestionDto;
import com.example.question_service.Questions.Models.Response;
import com.example.question_service.Questions.Services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllquestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PostMapping("/")
    public ResponseEntity<String> createQuizz(@RequestBody Question question){
        questionService.createQuestion(question);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PostMapping("/many")
    public ResponseEntity<String> createManyQuestion (@RequestBody List<Question> questions){
        questionService.createQuestions(questions);
        return ResponseEntity.ok("Questions Created");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable int id){
        try {
            Question question = questionService.getQuestion(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        try {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question){
        questionService.updateQuestion(id, question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestions(category, numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore (@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}
