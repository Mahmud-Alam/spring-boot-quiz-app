package com.mahmudalam.quizapp.service;

import com.mahmudalam.quizapp.dao.QuestionDao;
import com.mahmudalam.quizapp.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<QuestionModel>> getAllQuestions(){
        try {
//            if (true) throw new RuntimeException("Forced exception");
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<QuestionModel> getQuestionById(Integer id) {
        try {
            Optional<QuestionModel> question = questionDao.findById(id);
            if (question.isPresent()) {
                return new ResponseEntity<>(question.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> createQuestion(QuestionModel question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question created successfully!",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question creation failed!",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Integer id, QuestionModel question) {
        try {
            Optional<QuestionModel> optionalQ = questionDao.findById(id);
            if (optionalQ.isPresent()) {
                QuestionModel existingQ = optionalQ.get();

                existingQ.setCategory(question.getCategory());
                existingQ.setDifficultyLevel(question.getDifficultyLevel());
                existingQ.setQuestionTitle(question.getQuestionTitle());
                existingQ.setOption1(question.getOption1());
                existingQ.setOption2(question.getOption2());
                existingQ.setOption3(question.getOption3());
                existingQ.setOption4(question.getOption4());
                existingQ.setAnswer(question.getAnswer());

                questionDao.save(existingQ);
                return new ResponseEntity<>("Question updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> patchUpdateQuestion(Integer id, QuestionModel question) {
        try {
            Optional<QuestionModel> optionalQ = questionDao.findById(id);
            if (optionalQ.isPresent()) {
                QuestionModel existingQ = optionalQ.get();

                if (question.getCategory() != null) existingQ.setCategory(question.getCategory());
                if (question.getDifficultyLevel() != null) existingQ.setDifficultyLevel(question.getDifficultyLevel());
                if (question.getQuestionTitle() != null) existingQ.setQuestionTitle(question.getQuestionTitle());
                if (question.getOption1() != null) existingQ.setOption1(question.getOption1());
                if (question.getOption2() != null) existingQ.setOption2(question.getOption2());
                if (question.getOption3() != null) existingQ.setOption3(question.getOption3());
                if (question.getOption4() != null) existingQ.setOption4(question.getOption4());
                if (question.getAnswer() != null) existingQ.setAnswer(question.getAnswer());

                questionDao.save(existingQ);
                return new ResponseEntity<>("Question patch updated successfully!", HttpStatus.OK);
            } else{
                return new ResponseEntity<>("Question not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (!questionDao.existsById(id)) {
                return new ResponseEntity<>("Question not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
