package com.mahmudalam.quizapp.service;

import com.mahmudalam.quizapp.dao.QuestionDao;
import com.mahmudalam.quizapp.dao.QuizDao;
import com.mahmudalam.quizapp.model.QuestionModel;
import com.mahmudalam.quizapp.model.QuestionWrapper;
import com.mahmudalam.quizapp.model.QuizModel;
import com.mahmudalam.quizapp.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<QuizModel> quiz = quizDao.findById(id);
        List<QuestionModel> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(QuestionModel q: questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add((qw));
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<String> createQuiz(String category, int limit, String title) {
        List<QuestionModel> questions = questionDao.findRandomQuestionsByCategory(category, limit);

        QuizModel quiz = new QuizModel();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        QuizModel quiz = quizDao.findById(id).get();
        List<QuestionModel> questions = quiz.getQuestions();

        int rightAnswer=0;
        int i=0;
        for(QuizResponse res: responses){
            if(res.getResponse().equals(questions.get(i).getAnswer())){
                rightAnswer++;
            }
            i++;
        }
        return new ResponseEntity<>(rightAnswer, HttpStatus.OK);
    }
}
