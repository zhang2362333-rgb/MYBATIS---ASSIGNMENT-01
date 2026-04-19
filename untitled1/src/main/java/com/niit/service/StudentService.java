package com.niit.service;

import com.niit.model.Student;
import java.util.List;

public interface StudentService {
    // Given Methods
    void insertStudent(Student s);
    Student getStudentByRollNumber(int roll_no);
    
    // Homework Methods
    List<Student> getAllStudents();
    void updateStudent(Student s);
    void deleteStudent(int id);
}
