package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enroll(int studentId, int courseId, LocalDate date) {
        // validate
        studentService.findById(studentId);
        courseService.findById(courseId);
        int id = IdGenerator.nextEnrollmentId();
        Enrollment e = new Enrollment(id, studentId, courseId, date);
        enrollments.add(e);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment " + id + " not found");
    }

    public Enrollment findByStudentId(int StudentId) {
        studentService.findById(StudentId);
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == StudentId) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment " + StudentId + " not found");
    }

    public void updateStatus(int enrollmentId, EnrollmentStatus status) {
        Enrollment e = findById(enrollmentId);
        e.setStatus(status);
    }
}
