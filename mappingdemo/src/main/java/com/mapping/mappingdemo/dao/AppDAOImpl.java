package com.mapping.mappingdemo.dao;

import com.mapping.mappingdemo.entity.Course;
import com.mapping.mappingdemo.entity.Instructor;
import com.mapping.mappingdemo.entity.InstructorDetail;
import com.mapping.mappingdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
    entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
       return entityManager.find(Instructor.class, id); //will also find Instructor Details because it is eager loading and loads everything
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempIns = entityManager.find(Instructor.class, id);

        List<Course> courses = tempIns.getCourse();
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        entityManager.remove(tempIns);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int id) {
        InstructorDetail tempInsDetail = entityManager.find(InstructorDetail.class, id);
        //remove the associated object references
        //break bidirectional link
        tempInsDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempInsDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data",
                Course.class);

        query.setParameter("data", id);

        //execute query
        List<Course> courses = query.getResultList();
        return courses;

    }

    @Override
    public Instructor findInstructorByJoinFetch(int id) {
        //create query
        //This will still retrieve Instructor and Courses
        //This JOIN FETCH is similar to EAGER loading
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.course " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :data", Instructor.class
        );
        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();
        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course tempCourse = entityManager.find(Course.class, id);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "Join Fetch c.reviews " +
                        "where c.id = :data" , Course.class
        );

        query.setParameter("data", theId);

        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                    "select c from Course c " +
                            "JOIN FETCH c.students " +
                            "where c.id = :data", Course.class
        );

        query.setParameter("data", theId);

        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int theId) {
        //create query
        TypedQuery<Student> query = entityManager.createQuery(
                    "select s from Student s " +
                            "JOIN FETCH s.courses " +
                            "where s.id = :data", Student.class
        );
        query.setParameter("data", theId);

        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        //retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        if(tempStudent != null){
            List<Course> courses = tempStudent.getCourses();

            //break the association
            for(Course tempCourse : courses){
                tempCourse.getStudents().remove(tempStudent);
            }

            entityManager.remove(tempStudent);

        }


    }


}
