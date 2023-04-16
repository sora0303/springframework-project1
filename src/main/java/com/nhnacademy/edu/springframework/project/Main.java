package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.repository.Student;

import java.util.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataLoadService dataLoadService = applicationContext.getBean(CsvDataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = applicationContext.getBean(DefaultStudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
    }
}
