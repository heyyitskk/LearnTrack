package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {
    private final CourseRepository repo = new CourseRepository();

    public Course addCourse(String name, String description, int weeks){
        int id = IdGenerator.nextCourseId();
        Course c = new Course(id, name, description, weeks);
        return repo.save(c);
    }

    public List<Course> listCourses() {
        return repo.findAll();
    }

    public Course findById(int id) {
        return repo.findById(id);
    }

    public void toggleActive(int id) {
        Course c = findById(id);
        c.setActive(!c.isActive());
    }
}
