package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;


    public Quiz addQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public Set<Quiz> getQuizzes(){
        return new HashSet<>(quizRepository.findAll());

    }

    public Quiz findQuizById(long id){
        return quizRepository.findById(id).get();
    }

    public void deleteQuiz(long qid){
        quizRepository.deleteById(qid);
    }

   public List<Quiz> findQuizByCategory(Category category){
        return this.quizRepository.findByCategory(category);

    }
}
