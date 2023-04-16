package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvScores implements Scores {

    private List<Score> scoreList = new ArrayList<>();

    @Override
    public void load() {
        if(!scoreList.isEmpty()){
            scoreList.clear();
        }
        String resourceName = "data" + File.separator + "score.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                Score score = new Score(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
                scoreList.add(score);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }


}
