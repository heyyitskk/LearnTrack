package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class StudentService {
    private final StudentRepository repo = new StudentRepository();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.nextStudentId();
        Student s = new Student(id, firstName, lastName, email, batch);
        return repo.save(s);
    }

    public List<Student> listStudents() {
        return repo.findAll();
    }

    public Student findById(int id) {
        return repo.findById(id);
    }

    public void deactivateStudent(int id) {
        Student s = findById(id);
        s.setActive(false);
    }
}