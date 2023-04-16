package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    private Students studentRepository;

    @Autowired
    public DefaultStudentService(Students students){
        this.studentRepository = students;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        return studentRepository.findAll().stream().filter(student -> !student.getScore().isFail()).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {

        return studentRepository.findAll().stream()
            .sorted((student1, student2) -> student2.getScore().getScore() - student1.getScore().getScore())
            .collect(Collectors.toList());
    }

}
