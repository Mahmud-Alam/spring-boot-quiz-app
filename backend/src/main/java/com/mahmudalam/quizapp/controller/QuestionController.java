package com.mahmudalam.quizapp.controller;

import com.mahmudalam.quizapp.model.QuestionModel;
import com.mahmudalam.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<QuestionModel> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("{id}")
    public Optional<QuestionModel> getQuestionById(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("category/{category}")
    public List<QuestionModel> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("create")
    public String createQuestion(@RequestBody QuestionModel question){
        return questionService.createQuestion(question);
    }
}
