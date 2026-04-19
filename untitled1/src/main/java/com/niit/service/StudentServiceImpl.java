package com.niit.service;

import com.niit.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private List<Student> students;

    public StudentServiceImpl() {
        this.students = new ArrayList<>();
    }

    // Given Methods Implementation
    @Override
    public void insertStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully!");
    }

    @Override
    public Student getStudentByRollNumber(int roll_no) {
        for (Student s : students) {
            if (s.getRoll_no() == roll_no) {
                return s;
            }
        }
        return null;
    }

    // Homework Methods Implementation
    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public void updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRoll_no() == s.getRoll_no()) {
                students.set(i, s);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found with Roll No: " + s.getRoll_no());
    }

    @Override
    public void deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRoll_no() == id) {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found with Roll No: " + id);
    }
}
