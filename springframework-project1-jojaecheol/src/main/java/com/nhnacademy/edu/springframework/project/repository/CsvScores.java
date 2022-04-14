package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CsvScores implements Scores {
    private final List<Score> scores = new ArrayList<>();
    private final Map<Integer, Integer> scores1 = new HashMap<>();

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/score.csv");
        ) {
            assert inputStream != null;
            try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    String[] token = line.split(",");
                    scores.add(new Score(Integer.parseInt(token[0]), Integer.parseInt(token[1])));
                    scores1.put(Integer.parseInt(token[0]), Integer.parseInt(token[1]));
                }
                loaded = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        return this.scores;
    }

//    @Override
//    public Map<Integer, Integer> findAll() {return this.scores1;}
}
