package com.mahmudalam.quizapp.dao;

import com.mahmudalam.quizapp.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionModel, Integer> {
    List<QuestionModel> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<QuestionModel> findRandomQuestionsByCategory(@Param("category") String category, @Param("limit") int limit);
}
