package com.nhnacademy.edu.springframework.project.repository;

import java.util.Collection;

public interface Students {
    void load();

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
