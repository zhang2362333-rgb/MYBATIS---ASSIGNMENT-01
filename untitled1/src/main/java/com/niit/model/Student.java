package com.niit.model;

import java.time.LocalDate;

public class Student {
    private int roll_no;
    private String name;
    private String course;
    private String grade;
    private LocalDate dob;
    private String gender;

    public Student() {
    }

    public Student(int roll_no, String name, String course, String grade, LocalDate dob, String gender) {
        this.roll_no = roll_no;
        this.name = name;
        this.course = course;
        this.grade = grade;
        this.dob = dob;
        this.gender = gender;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-20s %-6s %-12s %-8s", roll_no, name, course, grade, dob, gender);
    }
}
