package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment save(Enrollment e) {
        enrollments.add(e);
        return e;
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment " + id + " not found");
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Enrollment for" + studentId + " not found");
        }
        return result;
    }
}
