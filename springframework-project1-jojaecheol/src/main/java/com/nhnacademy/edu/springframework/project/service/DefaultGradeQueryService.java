package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    private final CsvStudents csvStudents;

    public DefaultGradeQueryService(
        CsvStudents csvStudents) {
        this.csvStudents = csvStudents;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {

        List<Student> listStudent = csvStudents.findAll().stream().filter(x -> x.getName().equals(name)).collect(
            Collectors.toList());

        List<Score> listScore = new ArrayList<>();

        for (int i = 0; i < listStudent.size(); ++i) {
            listScore.add(listStudent.get(i).getScore());
        }

        return listScore;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        return csvStudents.getStudents().get(seq).getScore();
    }
}
