package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Students;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {
    private Students students;

    public DefaultStudentService(
        Students students) {
        this.students = students;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        return students.findAll().stream().filter(s -> !s.getScore().isFail()).collect(
            Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        return students.findAll().stream().sorted().collect(Collectors.toList());
    }

}
