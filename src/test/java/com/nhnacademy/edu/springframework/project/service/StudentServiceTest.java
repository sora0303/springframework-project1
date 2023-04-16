package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final Students csvStudents = CsvStudents.getInstance();
    private final Field fieldStudent = ReflectionUtils.findField(CsvStudents.class, "studentsMap");
    private StudentService studentService;

    @BeforeEach
    void setup() {
        studentService = new DefaultStudentService();
        ReflectionUtils.makeAccessible(fieldStudent);

        Map<Integer, Student> testStudensMap = new HashMap<>();
        testStudensMap.put(1, new Student(1, "A"));
        testStudensMap.get(1).setScore(new Score(new Integer(1), 30));
        testStudensMap.put(2, new Student(2, "B"));
        testStudensMap.get(2).setScore(new Score(new Integer(2), 80));
        testStudensMap.put(3, new Student(3, "A"));
        testStudensMap.get(3).setScore(new Score(new Integer(3), 70));
        ReflectionUtils.setField(fieldStudent, csvStudents, testStudensMap);
    }

    @Test
    void getPassedStudents() {

        List<Student> getPassedStudentsList = studentService.getPassedStudents().stream().collect(Collectors.toList());

        assertAll(
            () -> assertTrue(getPassedStudentsList.get(0).getName().equals("B")),
            () -> assertTrue(getPassedStudentsList.get(0).getSeq() == 2),
            () -> assertTrue(getPassedStudentsList.get(0).getScore().getScore() == 80),
            () -> assertTrue(getPassedStudentsList.get(1).getName().equals("A")),
            () -> assertTrue(getPassedStudentsList.get(1).getSeq() == 3),
            () -> assertTrue(getPassedStudentsList.get(1).getScore().getScore() == 70)
        );
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> getStudentsOrderByScoreList = studentService.getStudentsOrderByScore().stream().collect(Collectors.toList());

        assertAll(
            () -> assertTrue(getStudentsOrderByScoreList.get(0).getName().equals("B")),
            () -> assertTrue(getStudentsOrderByScoreList.get(0).getSeq() == 2),
            () -> assertTrue(getStudentsOrderByScoreList.get(0).getScore().getScore() == 80),
            () -> assertTrue(getStudentsOrderByScoreList.get(1).getName().equals("A")),
            () -> assertTrue(getStudentsOrderByScoreList.get(1).getSeq() == 3),
            () -> assertTrue(getStudentsOrderByScoreList.get(1).getScore().getScore() == 70),
            () -> assertTrue(getStudentsOrderByScoreList.get(2).getName().equals("A")),
            () -> assertTrue(getStudentsOrderByScoreList.get(2).getSeq() == 1),
            () -> assertTrue(getStudentsOrderByScoreList.get(2).getScore().getScore() == 30)

        );
    }
}