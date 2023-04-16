package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Student;
import java.util.Collection;

public interface StudentService {
    Collection<Student> getPassedStudents();

    Collection<Student> getStudentsOrderByScore();
}
