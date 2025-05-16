package com.mahmudalam.quizapp.service;

import com.mahmudalam.quizapp.dao.QuestionDao;
import com.mahmudalam.quizapp.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<QuestionModel> getAllQuestions() {
        return questionDao.findAll();
    }

    public Optional<QuestionModel> getQuestionById(Integer id) {
        return questionDao.findById(id);
    }
    
    public List<QuestionModel> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String createQuestion(QuestionModel question) {
        questionDao.save(question);
        return "Success! - 200";
    }

}
