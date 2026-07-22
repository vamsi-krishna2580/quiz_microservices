package com.example.quiz_service.Quizz.Services;



import com.example.quiz_service.Quizz.Client.QuestionClient;
import com.example.quiz_service.Quizz.Models.QuestionDto;
import com.example.quiz_service.Quizz.Models.Quizz;
import com.example.quiz_service.Quizz.Models.Response;
import com.example.quiz_service.Quizz.Repositories.QuizzRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizzService {

    private final QuizzRepo quizzRepo;
    private final QuestionClient questionClient;

    public QuizzService(QuizzRepo quizzRepo, QuestionClient questionClient) {
        this.quizzRepo = quizzRepo;
        this.questionClient = questionClient;
    }

    public ResponseEntity<List<QuestionDto>> getQuizz(Integer id) {
           Quizz quizz = quizzRepo.findById(id).orElseThrow(()-> new RuntimeException("Quizz not found"));
            List<Integer> questionIds = quizz.getQuestionIds();
            List<QuestionDto> questionDtos = questionClient.getQuestions(questionIds).getBody();
            return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    public ResponseEntity<String> createQuizz(String title, String category, int numQ) {
        List<Integer> questionsIds = questionClient.generateQuestions(category, numQ).getBody();
        Quizz quizz = new Quizz();
        quizz.setTitle(title);
        quizz.setQuestionIds(questionsIds);
        quizzRepo.save(quizz);

        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz Created with id = "+quizz.getId());
    }

    public ResponseEntity<Integer> caluclateResult(Integer id, List<Response> responses){
        Quizz quizz = quizzRepo.findById(id).orElseThrow(()-> new RuntimeException("Quiz not found"));
        Integer right = questionClient.getScore(responses).getBody();
        return ResponseEntity.ok(right);
    }

    public ResponseEntity<List<Quizz>> getAllQuiz() {
        return ResponseEntity.ok(quizzRepo.findAll());
    }
}

