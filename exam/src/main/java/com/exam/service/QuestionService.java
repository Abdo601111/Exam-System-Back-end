package com.exam.service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;

    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question){
        return questionRepository.save(question);
    }

    public Set<Question> getQuestion(){
        return new HashSet<>(questionRepository.findAll());

    }

    public Question findQuestionById(long id){
        return questionRepository.findById(id).get();
    }

    public void deleteQuestion(long id){
        questionRepository.deleteById(id);
    }

    public Set<Question> getQuestionOfQuiz(Quiz quiz){
        return questionRepository.findByQuiz(quiz);

    }
}
