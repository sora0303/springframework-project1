package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    DataLoadService csvDataLoadService = applicationContext.getBean(CsvDataLoadService.class);

    @Test
    void loadAndMerge() {
        assertDoesNotThrow(() -> csvDataLoadService.loadAndMerge());
    }
}