package com.niit;

import com.niit.model.Student;
import com.niit.service.StudentService;
import com.niit.service.StudentServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Thank you for using Student Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("========================================");
        System.out.println("=====STUDENT MANAGEMENT SYSTEM=====");
        System.out.println("========================================");
        System.out.println("1.Add Student");
        System.out.println("2.View All Students");
        System.out.println("3.Update Student");
        System.out.println("4.Delete Student");
        System.out.println("5.Exit");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        int roll_no = getIntInput("Enter Roll No: ");
        
        // Check if roll number already exists
        if (studentService.getStudentByRollNumber(roll_no) != null) {
            System.out.println("Error: Student with Roll No " + roll_no + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine().toUpperCase();
        LocalDate dob = getDateInput("Enter DOB (yyyy-MM-dd): ");
        String gender = getGenderInput("Enter Gender (MALE/FEMALE): ");

        Student student = new Student(roll_no, name, course, grade, dob, gender);
        studentService.insertStudent(student);
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-6s %-12s %-8s%n", "Roll No", "Name", "Course", "Grade", "DOB", "Gender");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int roll_no = getIntInput("Enter Roll No to update: ");
        
        Student existingStudent = studentService.getStudentByRollNumber(roll_no);
        if (existingStudent == null) {
            System.out.println("Error: Student with Roll No " + roll_no + " not found!");
            return;
        }

        System.out.println("Current student info: " + existingStudent);
        System.out.print("Enter new Name (or press Enter to keep current): ");
        String nameInput = scanner.nextLine();
        String name = nameInput.isEmpty() ? existingStudent.getName() : nameInput;

        System.out.print("Enter new Course (or press Enter to keep current): ");
        String courseInput = scanner.nextLine();
        String course = courseInput.isEmpty() ? existingStudent.getCourse() : courseInput;

        System.out.print("Enter new Grade (or press Enter to keep current): ");
        String gradeInput = scanner.nextLine();
        String grade = gradeInput.isEmpty() ? existingStudent.getGrade() : gradeInput.toUpperCase();
        
        System.out.print("Enter new DOB (yyyy-MM-dd) (or press Enter to keep current): ");
        String dobInput = scanner.nextLine();
        LocalDate dob = dobInput.isEmpty() ? existingStudent.getDob() : LocalDate.parse(dobInput);
        
        System.out.print("Enter new Gender (MALE/FEMALE) (or press Enter to keep current): ");
        String genderInput = scanner.nextLine();
        String gender = genderInput.isEmpty() ? existingStudent.getGender() : genderInput.toUpperCase();

        Student updatedStudent = new Student(roll_no, name, course, grade, dob, gender);
        studentService.updateStudent(updatedStudent);
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int roll_no = getIntInput("Enter Roll No to delete: ");
        studentService.deleteStudent(roll_no);
    }

    private static LocalDate getDateInput(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd format.");
            }
        }
    }

    private static String getGenderInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String gender = scanner.nextLine().toUpperCase();
            if (gender.equals("MALE") || gender.equals("FEMALE")) {
                return gender;
            }
            System.out.println("Invalid input! Please enter MALE or FEMALE.");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}