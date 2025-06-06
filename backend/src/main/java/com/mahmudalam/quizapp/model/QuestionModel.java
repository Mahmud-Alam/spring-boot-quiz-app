package com.mahmudalam.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity()
@Table(name="question")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String difficultyLevel;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
}
