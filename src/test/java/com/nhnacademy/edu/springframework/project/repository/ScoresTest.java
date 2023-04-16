package com.nhnacademy.edu.springframework.project.repository;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;


import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    private Scores csvScores;
    private Field field;

    @BeforeEach
    void setUp(){
        csvScores = CsvScores.getInstance();
        field = ReflectionUtils.findField(CsvScores.class, "scoreList");
        ReflectionUtils.makeAccessible(field);
    }

    @Test
    void load() {
        assertDoesNotThrow(() -> csvScores.load());

        final List<Score> scoreList = (List<Score>) ReflectionUtils.getField(field, csvScores);
        assertTrue(() -> scoreList.size() == 3);
    }


    @Test
    void findAll() {
        List<Score> testScoreList = new ArrayList<>();
        testScoreList.add(new Score(new Integer(1), 30));
        testScoreList.add(new Score(new Integer(2), 80));
        testScoreList.add(new Score(new Integer(3), 70));
        ReflectionUtils.setField(field, csvScores, testScoreList);

        assertEquals(1, csvScores.findAll().get(0).getStudentSeq());
        assertEquals(30, csvScores.findAll().get(0).getScore());
        assertEquals(2, csvScores.findAll().get(1).getStudentSeq());
        assertEquals(80, csvScores.findAll().get(1).getScore());
        assertEquals(3, csvScores.findAll().get(2).getStudentSeq());
        assertEquals(70, csvScores.findAll().get(2).getScore());
    }
}