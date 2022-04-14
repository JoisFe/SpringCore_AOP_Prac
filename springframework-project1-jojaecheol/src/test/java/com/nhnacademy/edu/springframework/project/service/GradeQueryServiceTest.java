package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class GradeQueryServiceTest {
    AnnotationConfigApplicationContext context;
    DataLoadService dataLoadService;
    StudentService studentService;
    DefaultGradeQueryService defaultGradeQueryService;


    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        studentService = context.getBean("defaultStudentService", DefaultStudentService.class);
        defaultGradeQueryService =
            context.getBean("defaultGradeQueryService", DefaultGradeQueryService.class);

    }

    @Test
    void getScoreByStudentName() {
        dataLoadService.loadAndMerge(); // 파일 데이터 읽음

        int[] studentSeq = {1, 7};
        int[] score = {30, 100};

        for (int i = 0; i < defaultGradeQueryService.getScoreByStudentName("조재철").size(); ++i) {
            assertThat(defaultGradeQueryService.getScoreByStudentName("조재철").get(i)
                .getStudentSeq()).isEqualTo(studentSeq[i]);
            assertThat(
                defaultGradeQueryService.getScoreByStudentName("조재철").get(i).getScore()).isEqualTo(
                score[i]);
        }
    }

    @Test
    void getScoreByStudentSeq() {
        dataLoadService.loadAndMerge();

        int score = 30;
        assertThat(defaultGradeQueryService.getScoreByStudentSeq(1).getScore()).isEqualTo(score);
    }
}

