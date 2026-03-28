package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public Course save(Course c) {
        courses.add(c);
        return c;
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Course findById(int id) {
        for(Course course : courses){
            if(course.getId() == id){
                return course;
            }
        }
        throw new EntityNotFoundException("Course " + id + " not found");
    }
}
