//package com.nhnacademy.edu.springframework.project.repository;
//
//import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ScoresTest {
//    AnnotationConfigApplicationContext context;
//
//    Scores scores;
//
//    @BeforeEach
//    void setUp() {
//        context = new AnnotationConfigApplicationContext(MainConfiguration.class);
//        scores = context.getBean("csvScores", CsvScores.class);
//    }
//    @Test
//    void load() {
//        assertThat(((CsvScores)scores).isLoaded()).isFalse();
//
//        // when
//        scores.load();
//
//        // then
//        assertThat(((CsvScores)scores).isLoaded()).isTrue();
//
//    }
//
//    @Test
//    void findAll() {
//        scores.load();
//        // when
//        List<Score> scoreList = scores.findAll();
//
//        int[] score = {30, 80, 70, 20, 40, 70, 100};
//
//        // then
//        for (int i = 0; i < score.length; ++i) {
//            assertThat(scoreList.get(i).getScore()).isEqualTo(score[i]);
//        }
//    }
//}