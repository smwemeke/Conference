package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Course;
import com.pluralsight.conference.model.Registration;
import com.pluralsight.conference.model.RegistrationReport;
import com.pluralsight.conference.respository.CourseRepository;
import com.pluralsight.conference.respository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;
    private CourseRepository courseRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository, CourseRepository courseRepository){
        this.registrationRepository = registrationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Registration addRegistration(Registration registration){
        registration = registrationRepository.save(registration);

        if(registration.getId() == null) {
            Course course = new Course();
            course.setName("Introductory Course");
            course.setDescription("Every attendee must complete the Introductory Course");
            course.setRegistration(registration);

            courseRepository.save(course);
        }


        return registration;
    }
    @Override
    public List<Registration> findAll(){
        return registrationRepository.findAll();
    }

   @Override
    public List<RegistrationReport> findAllReports(){
        return registrationRepository.registrationReport();
    }
}
