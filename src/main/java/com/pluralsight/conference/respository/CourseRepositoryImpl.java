package com.pluralsight.conference.respository;

import com.pluralsight.conference.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public Course save(Course course){
       entityManager.persist(course);
       return course;
   }
}
