package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private final int seq;
    private final String name;
    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}'+ '\n';
    }

    @Override
    public int compareTo(Student s) {
        return s.getScore().getScore() - this.score.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return seq == student.seq && Objects.equals(name, student.name) &&
            Objects.equals(score, student.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, name, score);
    }
}
