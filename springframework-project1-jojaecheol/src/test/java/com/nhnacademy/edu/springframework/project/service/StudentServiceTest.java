package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class StudentServiceTest {
    AnnotationConfigApplicationContext context;
    Scores scores;
    Students students;
    DataLoadService dataLoadService;
    StudentService studentService;


    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        studentService = context.getBean("defaultStudentService", DefaultStudentService.class);


    }

    @Test
    void getPassedStudents() {
        dataLoadService.loadAndMerge();

        Collection<Student> passedStudents = studentService.getPassedStudents();

        List<Student> listPassedStudents = (List<Student>) passedStudents;

        int[] passedStudentsScore = new int[listPassedStudents.size()];

        // when
        for (int i = 0; i < listPassedStudents.size(); ++i) {
            passedStudentsScore[i] = listPassedStudents.get(i).getScore().getScore();
            System.out.println(passedStudentsScore[i]);
        }

        // then
        for (int i = 0; i < passedStudentsScore.length; ++i) {
            assertThat(passedStudentsScore[i] >= 60).isTrue();
        }
    }

    @Test
    void getStudentsOrderByScore() {
        dataLoadService.loadAndMerge();

        Collection<Student> getStudentsOrderByScore = studentService.getStudentsOrderByScore();

        List<Student> listOrderedStudents = (List<Student>) getStudentsOrderByScore;

        int[] orderedStudentsScore = new int[listOrderedStudents.size()];

        // when
        for (int i = 0; i < listOrderedStudents.size(); ++i) {
            orderedStudentsScore[i] = listOrderedStudents.get(i).getScore().getScore();
            System.out.println(orderedStudentsScore[i]);
        }

        // then
        for (int i = 0; i < orderedStudentsScore.length -1; ++i) {
            assertThat(orderedStudentsScore[i] >= orderedStudentsScore[i + 1]).isTrue();
        }

    }
}