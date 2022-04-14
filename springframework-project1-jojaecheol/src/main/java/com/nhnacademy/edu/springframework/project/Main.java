package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(MainConfiguration.class);

        DataLoadService dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);

        Collection<Student> passedStudents = studentService.getPassedStudents();
        log.info(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        log.info(orderedStudents);

    }
}
