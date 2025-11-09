package com.mapping.mappingdemo.dao;

import com.mapping.mappingdemo.entity.Course;
import com.mapping.mappingdemo.entity.Instructor;
import com.mapping.mappingdemo.entity.InstructorDetail;
import com.mapping.mappingdemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailsById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByJoinFetch(int id);

    void update(Instructor tempInstructor);

    void update(Course tempCourse);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCourseByStudentId(int theId);

    void update(Student tempStudent);

    void deleteStudentById(int theId);

}