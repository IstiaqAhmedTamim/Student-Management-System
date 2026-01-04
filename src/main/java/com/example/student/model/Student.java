package com.example.student.model;

public class Student implements Comparable<Student> {

    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    // Sort by ID (ascending)
    @Override
    public int compareTo(Student s) {
        return Integer.compare(this.id, s.id);
    }
}
