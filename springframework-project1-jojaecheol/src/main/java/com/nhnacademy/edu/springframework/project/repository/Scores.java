package com.nhnacademy.edu.springframework.project.repository;

import java.util.List;
import java.util.Map;

public interface Scores {
    void load();

    List<Score> findAll();
    //Map<Integer, Integer> findAll();
}
