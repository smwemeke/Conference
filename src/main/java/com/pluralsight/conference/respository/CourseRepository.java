package com.pluralsight.conference.respository;

import com.pluralsight.conference.model.Course;

public interface CourseRepository {
    Course save(Course course);
}
