package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository repo = new EnrollmentRepository();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enroll(int studentId, int courseId, LocalDate date) {
        studentService.findById(studentId);
        courseService.findById(courseId);
        int id = IdGenerator.nextEnrollmentId();
        Enrollment e = new Enrollment(id, studentId, courseId, date);
        return repo.save(e);
    }

    public Enrollment findById(int id) {
        return repo.findById(id);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        studentService.findById(studentId);
        return repo.findByStudentId(studentId);
    }

    public void updateStatus(int enrollmentId, EnrollmentStatus status) {
        Enrollment e = findById(enrollmentId);
        try {
            e.setStatus(status);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
