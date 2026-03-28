package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public Student save(Student s) {
        students.add(s);
        return s;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Student findById(int id) {
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        throw new EntityNotFoundException("Student " + id + " not found");
    }
}
