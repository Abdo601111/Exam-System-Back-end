package com.exam.controller;


import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;
    private final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        Question question1= questionService.addQuestion(question);
        return  ResponseEntity.ok(question1);
    }

    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId){
        return questionService.findQuestionById(quesId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions(@RequestBody Question question){
        return ResponseEntity.ok(questionService.getQuestion());
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question){
        Question question1= questionService.addQuestion(question);
        return  ResponseEntity.ok(question1);
    }

//    @GetMapping("/{quesId}")
//    public void deleteQuiz(@PathVariable("quesId") Long quesId){
//
//        questionService.deleteQuestion(quesId);
//    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionQuiz(@PathVariable("qid")Long qid){
//         Quiz quiz= new Quiz();
//         quiz.setQid(qid);
//         Set<Question> questionSet=questionService.getQuestionOfQuiz(quiz);

        Quiz quiz=quizService.findQuizById(qid);
        Set<Question> questionSet= quiz.getQuestions();
        List list = new ArrayList(questionSet);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestion())){
            list= list.subList(0,Integer.parseInt(quiz.getNumberOfQuestion()+1));
        }
        Collections.shuffle(list);
        return  ResponseEntity.ok(list);
    }


    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionQuizAdmin(@PathVariable("qid") long qid){
         Quiz quiz= new Quiz();
         quiz.setQid(qid);
         Set<Question> questionSet=questionService.getQuestionOfQuiz(quiz);


        return  ResponseEntity.ok(questionSet);
    }


    @DeleteMapping("/{quesId}")
    public void DeleteQuestion(@PathVariable("quesId") long quesId){
         questionService.deleteQuestion(quesId);
    }
}
