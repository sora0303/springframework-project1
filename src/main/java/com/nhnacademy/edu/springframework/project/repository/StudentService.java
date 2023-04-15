package com.nhnacademy.edu.springframework.project.repository;

import java.util.Collection;

public interface StudentService {
    Collection<Student> getPassedStudents();

    Collection<Student> getStudentsOrderByScore();
}
