package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.AppConfig;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Students csvStudents = applicationContext.getBean(CsvStudents.class);
    private final Field fieldStudent = ReflectionUtils.findField(CsvStudents.class, "studentsMap");
    private GradeQueryService gradeQueryService;

    @BeforeEach
    void setup() {
        gradeQueryService = applicationContext.getBean(DefaultGradeQueryService.class);
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
    void getScoreByStudentName() {
        assertTrue(gradeQueryService.getScoreByStudentName("A").get(0).getStudentSeq() == 1);
        assertTrue(gradeQueryService.getScoreByStudentName("A").get(0).getScore() == 30);
        assertTrue(gradeQueryService.getScoreByStudentName("A").get(1).getStudentSeq() == 3);
        assertTrue(gradeQueryService.getScoreByStudentName("A").get(1).getScore() == 70);
        assertTrue(gradeQueryService.getScoreByStudentName("B").get(0).getStudentSeq() == 2);
        assertTrue(gradeQueryService.getScoreByStudentName("B").get(0).getScore() == 80);

    }

    @Test
    void getScoreByStudentSeq() {
        assertTrue(gradeQueryService.getScoreByStudentSeq(1).getScore() == 30);
        assertTrue(gradeQueryService.getScoreByStudentSeq(2).getScore() == 80);
        assertTrue(gradeQueryService.getScoreByStudentSeq(3).getScore() == 70);
    }
}