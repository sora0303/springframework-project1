package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;

import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private Students students;

    @Autowired
    public DefaultGradeQueryService(Students students){
        this.students = students;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        return students.findAll().stream().filter(student -> student.getName().equalsIgnoreCase(name))
            .map(Student::getScore)
            .collect(
                Collectors.toList());

    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        return students.findAll().stream().filter(student -> student.getSeq() == seq)
            .map(Student::getScore).findFirst().orElse(null);
    }
}
