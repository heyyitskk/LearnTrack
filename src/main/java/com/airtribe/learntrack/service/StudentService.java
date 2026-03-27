package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.nextStudentId();
        Student s = new Student(id, firstName, lastName, email, batch);
        students.add(s);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student not found: " + id);
    }

    public void deactivateStudent(int id) {
        Student s = findById(id);
        s.setActive(false);
    }
}