package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class DataLoadServiceTest {
    AnnotationConfigApplicationContext context;
    Scores scores;
    Students students;
    DataLoadService dataLoadService;


    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        scores = context.getBean("csvScores", CsvScores.class);
        students = context.getBean("csvStudents", CsvStudents.class);
        dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
    }

    @Test
    void loadAndMerge() {
        int[] postMergeStudentSeq = {1, 2, 3, 4, 5, 6, 7};
        int[] postMergeScoreList = {30, 80, 70, 20, 40, 70, 100};

        // loadAndMerge 전 상황들
        assertThat(((CsvScores)scores).isLoaded()).isFalse();
        assertThat(((CsvStudents)students).isLoaded()).isFalse();

        assertThat(((CsvStudents) students).getStudents()).isEmpty();

        // when
        dataLoadService.loadAndMerge();

        // loadAndMerge 후 상황들
        assertThat(((CsvScores)scores).isLoaded()).isTrue();
        assertThat(((CsvStudents)students).isLoaded()).isTrue();

        for (int i = 0; i < students.findAll().size(); ++i) {
            assertThat(students.findAll().stream().collect(Collectors.toList()).get(i).getScore().getStudentSeq()).isEqualTo(postMergeStudentSeq[i]);
            assertThat(students.findAll().stream().collect(Collectors.toList()).get(i).getScore().getScore()).isEqualTo(postMergeScoreList[i]);
        }
    }
}