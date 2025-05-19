package com.mahmudalam.quizapp.service;

import com.mahmudalam.quizapp.dao.QuestionDao;
import com.mahmudalam.quizapp.dao.QuizDao;
import com.mahmudalam.quizapp.model.QuestionModel;
import com.mahmudalam.quizapp.model.QuizModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int limit, String title) {
        List<QuestionModel> questions = questionDao.findRandomQuestionsByCategory(category, limit);

        QuizModel quiz = new QuizModel();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
    }
}
