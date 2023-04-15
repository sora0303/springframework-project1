package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CsvScores implements Scores {

    private List<Score> scoreList;

    private CsvScores() {
        scoreList = new ArrayList<>();
    }

    private static class CsvScoresSingleton {
        public static final CsvScores INSTANCE = new CsvScores();
    }

    public static Scores getInstance() {
        return CsvScoresSingleton.INSTANCE;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
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
