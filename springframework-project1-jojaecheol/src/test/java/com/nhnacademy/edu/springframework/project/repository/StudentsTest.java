//package com.nhnacademy.edu.springframework.project.repository;
//
//import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
//import com.nhnacademy.edu.springframework.project.service.Student;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class StudentsTest {
//    AnnotationConfigApplicationContext context;
//    Scores scores;
//    Students students;
//
//    @BeforeEach
//    void setUp() {
//       context = new AnnotationConfigApplicationContext(MainConfiguration.class);
//       scores = context.getBean("csvScores", CsvScores.class);
//       students = context.getBean("csvStudents", CsvStudents.class);
//    }
//
//    @Test
//    void load() {
//        // when
//        students.load();
//
//        // then
//        assertThat(((CsvStudents)students).isLoaded()).isTrue();
//    }
//
//    @Test
//    void findAll() {
//        scores.load();
//        List<Score> scoreList = scores.findAll();
//
//        students.load();
//
//        // when
//        Map<Integer, Student> mapStudents = ((CsvStudents)students).getStudents();
//
//        String[] str = {"조재철", "이제훈", "김훈민", "최정우", "최겸준", "조현진", "조재철"};
//
//        // then
//        for (int i = 0; i < str.length; ++i) {
//            assertThat(mapStudents.get(scoreList.get(i).getStudentSeq()).getName()).isEqualTo(str[i]);
//        }
//    }
//
//    @Test
//    void merge() {
//        scores.load();
//        students.load();
//
//        int[] postMergeStudentSeq = {1, 2, 3, 4, 5, 6, 7};
//        int[] postMergeScoreList = {30, 80, 70, 20, 40, 70, 100};
//
//        for (int i = 0; i < students.findAll().size(); ++i) {
//            assertThat(students.findAll().stream().collect(Collectors.toList()).get(i).getScore()).isNull();
//        }
//
//        students.merge(scores.findAll());
//
//        for (int i = 0; i < students.findAll().size(); ++i) {
//            assertThat(students.findAll().stream().collect(Collectors.toList()).get(i).getScore().getStudentSeq()).isEqualTo(postMergeStudentSeq[i]);
//            assertThat(students.findAll().stream().collect(Collectors.toList()).get(i).getScore().getScore()).isEqualTo(postMergeScoreList[i]);
//        }
//
//    }
//}