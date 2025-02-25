package com.example.StudentResultPublisher.controller;

import com.example.StudentResultPublisher.model.Student;
import com.example.StudentResultPublisher.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/studentEntry")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student added successfully!");
    }

    @GetMapping("/viewStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PutMapping("/calculateGrades")
    public ResponseEntity<String> calculateGrades() {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            Map<String, String> grades = new HashMap<>();
            boolean failed = false;

            for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
                String subject = entry.getKey();
                int marks = entry.getValue();
                String grade = calculateGrade(marks, 100);

                grades.put(subject, grade);
                if (grade.equals("F")) failed = true;
            }

            student.setSubjectGrades(grades);
            student.setFinalQualificationStatus(failed ? "Failed" : "Passed");
            studentRepository.save(student);
        }

        return ResponseEntity.ok("Grades calculated and updated!");
    }

    private String calculateGrade(int marks, int maxMarks) {
        double percentage = ((double) marks / maxMarks) * 100;

        if (percentage >= 90) return "S";
        if (percentage >= 85) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 75) return "B+";
        if (percentage >= 70) return "B";
        if (percentage >= 65) return "C+";
        if (percentage >= 60) return "C";
        if (percentage >= 55) return "D+";
        if (percentage >= 50) return "D";
        return "F";
    }
}
