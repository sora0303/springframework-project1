package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CsvStudents implements Students {

    Map<Integer, Student> studentsMap;

    private CsvStudents() {
        studentsMap = new ConcurrentHashMap<>();
    }

    ;

    private static class CsvStudentsSingleton {
        private static final CsvStudents INSTANCE = new CsvStudents();
    }

    public static Students getInstance() {
        return CsvStudentsSingleton.INSTANCE;
    }

    @Override
    public void load() {
        studentsMap.clear();
        String resourceName = "data" + File.separator + "student.csv";
        System.out.println(resourceName);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                Student student = new Student(Integer.parseInt(splitLine[0]), splitLine[1]);
                studentsMap.put(Integer.parseInt(splitLine[0]), student);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentsMap.values();
    }

    @Override
    public void merge(Collection<Score> scores) {
        scores.stream().forEach(score -> studentsMap.get(score.getStudentSeq()).setScore(score));
    }

}
