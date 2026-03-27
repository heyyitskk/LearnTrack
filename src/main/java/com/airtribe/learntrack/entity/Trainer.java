package com.airtribe.learntrack.entity;

import java.util.ArrayList;

public class Trainer extends Person{
    private ArrayList<String> subjects;
    private ArrayList<Student> students;

    public Trainer() {}

    public Trainer(int id, String firstName, String lastName, String email, ArrayList<String> subjects, ArrayList<Student> students) {
        super(id, firstName, lastName, email);
        this.subjects = subjects;
        this.students = students;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
