package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class CsvStudents implements Students {
    private final Map<Integer,Student> students = new HashMap<>();

    private boolean loaded;

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/student.csv");
        ) {
            assert inputStream != null;
            try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                ) {
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    String[] token = line.split(",");
                    students.put(Integer.parseInt(token[0]), new Student(Integer.parseInt(token[0]), token[1]));
                }

                loaded = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return this.students.values();
    }

    @Override
    public void merge(Collection<Score> scores) {
        scores.forEach(s -> students.get(s.getStudentSeq()).setScore(s));
    }


//    @Override
//    public void merge(Map<Integer, Integer> scores) {
//        // ((HashMap)scores).forEach(s -> students.get(s));
//        for (int i = 0; i < students.size(); ++i) {
//            students.get(i).setScore(scores.get(i));
//        }
//    }
}
