package com.mahmudalam.quizapp.dao;

import com.mahmudalam.quizapp.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizModel, Integer> {
}
