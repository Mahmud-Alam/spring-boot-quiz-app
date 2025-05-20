package com.mahmudalam.quizapp.controller;

import com.mahmudalam.quizapp.model.QuestionWrapper;
import com.mahmudalam.quizapp.model.QuizResponse;
import com.mahmudalam.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int limit, @RequestParam String title){
        return quizService.createQuiz(category, limit, title);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> responses){
        return quizService.calculateResult(id, responses);
    }
}
