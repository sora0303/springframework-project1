package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;

import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students students = CsvStudents.getInstance();
        return students.findAll().stream().filter(student -> student.getName().equalsIgnoreCase(name))
            .map(Student::getScore)
            .collect(
                Collectors.toList());

    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Students students = CsvStudents.getInstance();
        return students.findAll().stream().filter(student -> student.getSeq() == seq)
            .map(Student::getScore).findFirst().orElse(null);
    }
}
