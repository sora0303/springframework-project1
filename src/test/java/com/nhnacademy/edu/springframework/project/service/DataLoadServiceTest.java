package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.Score;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    DataLoadService csvDataLoadService = new CsvDataLoadService();

    @Test
    void loadAndMerge() {
        assertDoesNotThrow(() -> csvDataLoadService.loadAndMerge());
    }
}