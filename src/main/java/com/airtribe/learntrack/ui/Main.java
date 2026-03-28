package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> addStudent();
                case "2" -> listStudents();
                case "3" -> searchStudent();
                case "4" -> deactivateStudent();
                case "5" -> addCourse();
                case "6" -> listCourses();
                case "7" -> toggleCourseActive();
                case "8" -> enrollStudent();
                case "9" -> listEnrollmentsForStudent();
                case "10" -> markEnrollmentStatus();
                case "0" -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Option not recognized. Try again.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("==== LearnTrack Menu ====");
        System.out.println(" 1) Add Student");
        System.out.println(" 2) List Students");
        System.out.println(" 3) Search Student");
        System.out.println(" 4) Deactivate Student");
        System.out.println(" 5) Add Course");
        System.out.println(" 6) List Courses");
        System.out.println(" 7) Toggle Course Active");
        System.out.println(" 8) Enroll Student in Course");
        System.out.println(" 9) List Enrollments For Student");
        System.out.println("10) Mark Enrollment Completed/Cancelled");
        System.out.println(" 0) Exit");
        System.out.print("Select option: ");
    }

    private static void addStudent() {
        System.out.print("First name: ");
        String first = scanner.nextLine().trim();
        System.out.print("Last name: ");
        String last = scanner.nextLine().trim();
        System.out.print("Email (optional): ");
        String email = scanner.nextLine().trim();
        System.out.print("Batch: ");
        String batch = scanner.nextLine().trim();
        Student s = studentService.addStudent(first, last, email.isEmpty() ? null : email, batch);
        System.out.println("Added student with id=" + s.getId());
    }

    private static void searchStudent() {
        System.out.print("Enter student id: ");
        String id = scanner.nextLine().trim();
        Student s = null;
        try {
            s = studentService.findById(Integer.parseInt(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
        System.out.println(s);
    }

    private static void listStudents() {
        List<Student> list = studentService.listStudents();
        if (list.isEmpty())
            System.out.println("No students found.");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    private static void deactivateStudent() {
        System.out.print("Student id to deactivate: ");
        String id = scanner.nextLine().trim();
        try {
            studentService.deactivateStudent(Integer.parseInt(id));
            System.out.println("Student deactivated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCourse() {
        System.out.print("Course name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Description: ");
        String desc = scanner.nextLine().trim();
        System.out.print("Duration in weeks: ");
        String d = scanner.nextLine().trim();
        try {
            int weeks = Integer.parseInt(d);
            Course c = courseService.addCourse(name, desc, weeks);
            System.out.println("Added course id=" + c.getId());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number for duration.");
        }
    }

    private static void listCourses() {
        List<Course> list = courseService.listCourses();
        if (list.isEmpty())
            System.out.println("No courses found.");
        list.forEach(System.out::println);
    }

    private static void toggleCourseActive() {
        System.out.print("Course id to toggle active: ");
        String id = scanner.nextLine().trim();
        try {
            courseService.toggleActive(Integer.parseInt(id));
            System.out.println("Toggled course active state.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enrollStudent() {
        System.out.print("Student id: ");
        String sid = scanner.nextLine().trim();
        System.out.print("Course id: ");
        String cid = scanner.nextLine().trim();
        try {
            Enrollment en = enrollmentService.enroll(Integer.parseInt(sid), Integer.parseInt(cid), LocalDate.now());
            System.out.println("Enrollment created id=" + en.getId());
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listEnrollmentsForStudent() {
        System.out.print("Student id: ");
        String sid = scanner.nextLine().trim();
        try {
            List<Enrollment> list = enrollmentService.findByStudentId(Integer.parseInt(sid));
            if (list.isEmpty())
                System.out.println("No enrollments found.");
            list.forEach(System.out::println);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private static void markEnrollmentStatus() {
        System.out.print("Enrollment id: ");
        String eid = scanner.nextLine().trim();
        System.out.print("Status (ACTIVE/COMPLETED/CANCELLED): ");
        String st = scanner.nextLine().trim();
        try {
            EnrollmentStatus status = EnrollmentStatus.valueOf(st.toUpperCase());
            enrollmentService.updateStatus(Integer.parseInt(eid), status);
            System.out.println("Updated status.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status or id format.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

