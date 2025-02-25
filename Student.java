package com.example.StudentResultPublisher.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollNumber;
    private String department;

    @ElementCollection
    private Map<String, Integer> subjectMarks = new HashMap<>();

    @ElementCollection
    private Map<String, String> subjectGrades = new HashMap<>();

    private String finalQualificationStatus;

    public Student() {}

    public Student(String name, String rollNumber, String department, Map<String, Integer> subjectMarks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.subjectMarks = subjectMarks;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Map<String, Integer> getSubjectMarks() { return subjectMarks; }
    public void setSubjectMarks(Map<String, Integer> subjectMarks) { this.subjectMarks = subjectMarks; }

    public Map<String, String> getSubjectGrades() { return subjectGrades; }
    public void setSubjectGrades(Map<String, String> subjectGrades) { this.subjectGrades = subjectGrades; }

    public String getFinalQualificationStatus() { return finalQualificationStatus; }
    public void setFinalQualificationStatus(String finalQualificationStatus) { this.finalQualificationStatus = f
