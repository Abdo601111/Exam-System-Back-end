package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1= quizService.addQuiz(quiz);
        return  ResponseEntity.ok(quiz1);
    }

    @GetMapping("/{qid}")
    public Quiz getQuiz(@PathVariable("qid") long qid){
        return quizService.findQuizById(qid);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllQuizzes(){
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        Quiz quiz1= quizService.addQuiz(quiz);
        return  ResponseEntity.ok(quiz1);
    }

    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") long qid){

        quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{qid}")
    public List<Quiz> getQuizByCategory(@PathVariable("qid") long qid){
        Category category = new Category();
        category.setCid(qid);
        return quizService.findQuizByCategory(category);
    }
}
