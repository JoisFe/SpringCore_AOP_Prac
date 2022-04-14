package com.nhnacademy.edu.springframework.project.repository;

public class Score {
    private final int studentSeq;
    private final int scores;

    public Score(int studentSeq, int score) {
        this.studentSeq = studentSeq;
        this.scores = score;
    }

    public int getStudentSeq() {
        return studentSeq;
    }

    public int getScore() {
        return scores;
    }

    public boolean isFail() {
        return (60 > this.scores);
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentSeq=" + studentSeq +
                ", score=" + scores +
                '}';
    }
}
