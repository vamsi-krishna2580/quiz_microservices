package com.example.question_service.Questions.Services;


import com.example.question_service.Questions.Models.Question;
import com.example.question_service.Questions.Models.QuestionDto;
import com.example.question_service.Questions.Models.Response;
import com.example.question_service.Questions.Repositories.QuestionRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;
    public QuestionService(QuestionRepo questionRepo){
        this.questionRepo = questionRepo;

    }

    public void createQuestion(Question question) {
        questionRepo.save(question);
    }
    public void createQuestions(List<Question> questions){
        questionRepo.saveAll(questions);
    }
    public Question getQuestion(int Id){
        return questionRepo.findById(Id).orElseThrow(()-> new RuntimeException("Not found"));
    }
    public void deleteQuestion(int Id){
        Optional<Question> question = questionRepo.findById(Id);
        if(question.isPresent()){
            questionRepo.deleteById(Id);
        }
    }
    public Question updateQuestion(int Id, Question updatedQuestion){
        Question question = questionRepo.findById(Id).orElseThrow();
        updatedQuestion.setId(question.getId());
        questionRepo.save(updatedQuestion);
        return updatedQuestion;
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, int numQ) {
        List<Integer> questionIds = questionRepo.findByRandomByCategory(category, numQ);
        return ResponseEntity.ok(questionIds);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        for(Integer questionId: questionIds) {
            questionDtos.add(
                    new QuestionDto(questionRepo.findById(questionId)
                            .orElseThrow(()-> new RuntimeException("Question not found")
                            )));
        }
        return ResponseEntity.ok(questionDtos);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response: responses){
            if(response.getResponse().
                    equals(questionRepo.findById(response.getId())
                            .orElseThrow(()-> new RuntimeException("Question not found"))
                            .getRightAnswer()
                    ))
                right ++;
        }
        return ResponseEntity.ok(right);
    }

}
