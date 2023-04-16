package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.AppConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Students csvStudents = applicationContext.getBean(CsvStudents.class);
    private final Scores csvScores = applicationContext.getBean(CsvScores.class);
    private final Field fieldScore = ReflectionUtils.findField(CsvScores.class, "scoreList");
    private final Field fieldStudent = ReflectionUtils.findField(CsvStudents.class, "studentsMap");

    @BeforeEach
    void setUp() {
        ReflectionUtils.makeAccessible(fieldScore);
        ReflectionUtils.makeAccessible(fieldStudent);
    }


    @Test
    void load() {
        assertDoesNotThrow(() -> csvStudents.load());
        final Map<Integer, Student> studentsMap = (HashMap<Integer, Student>) ReflectionUtils.getField(fieldStudent, csvStudents);
        assertTrue(() -> studentsMap.size() == 3);
    }

    @Test
    void findAll() {
        Map<Integer, Student> testStudensMap = new HashMap<>();
        testStudensMap.put(1, new Student(1, "A"));
        testStudensMap.put(2, new Student(2, "B"));
        testStudensMap.put(3, new Student(3, "A"));
        testStudensMap.put(4, new Student(4, "D"));
        ReflectionUtils.setField(fieldStudent, csvStudents, testStudensMap);

        assertAll(
            () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(0).getSeq() == 1),
                () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(0).getName().equals("A")),
                () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(1).getSeq() == 2),
                () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(1).getName().equals("B")),
                () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(2).getSeq() == 3),
            () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(2).getName().equals("A")),
            () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(3).getSeq() == 4),
            () -> assertTrue(csvStudents.findAll().stream().collect(Collectors.toList()).get(3).getName().equals("D"))
        );
    }

    @Test
    void merge() {
        Map<Integer, Student> testStudensMap = new HashMap<>();
        testStudensMap.put(1, new Student(1, "A"));
        testStudensMap.put(2, new Student(2, "B"));
        testStudensMap.put(3, new Student(3, "A"));
        ReflectionUtils.setField(fieldStudent, csvStudents, testStudensMap);
        Map<Integer, Student> studentsMap = (Map<Integer, Student>) ReflectionUtils.getField(fieldStudent, csvStudents);

        List<Score> testScoreList = new ArrayList<>();
        testScoreList.add(new Score(new Integer(1), 30));
        testScoreList.add(new Score(new Integer(2), 80));
        testScoreList.add(new Score(new Integer(3), 70));
        ReflectionUtils.setField(fieldScore, csvScores, testScoreList);
        List<Score> scoreList = (List<Score>) ReflectionUtils.getField(fieldScore, csvScores);

        assertTrue(scoreList.size() == studentsMap.values().size());
        assertDoesNotThrow(() -> csvStudents.merge(scoreList));
    }
}