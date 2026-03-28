package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String name, String description, int weeks){
        int id = IdGenerator.nextCourseId();
        Course c = new Course(id, name, description, weeks);
        courses.add(c);
        return c;
    }

    public List<Course> listCourses() {
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

    public void toggleActive(int id) {
        Course c = findById(id);
        c.setActive(!c.isActive());
    }
}
