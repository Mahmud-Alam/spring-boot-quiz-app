package com.mahmudalam.quizapp.controller;

import com.mahmudalam.quizapp.model.QuestionModel;
import com.mahmudalam.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<QuestionModel> getQuestionById(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionModel question){
        return questionService.createQuestion(question);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody QuestionModel question){
        return questionService.updateQuestion(id, question);
    }

    @PatchMapping("patch/{id}")
    public ResponseEntity<String> patchUpdateQuestion(@PathVariable Integer id, @RequestBody QuestionModel question){
        return questionService.patchUpdateQuestion(id, question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
}
