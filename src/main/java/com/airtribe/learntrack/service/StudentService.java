package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Student> findById(int id){
        return students.stream().filter(x -> x.getId() == id).findFirst();
    }

    public void deactivateStudent(int id) {
        Student s = findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
        s.setActive(false);
    }
}