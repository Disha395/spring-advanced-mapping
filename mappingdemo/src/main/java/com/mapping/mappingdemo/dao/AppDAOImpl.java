package com.mapping.mappingdemo.dao;

import com.mapping.mappingdemo.entity.Course;
import com.mapping.mappingdemo.entity.Instructor;
import com.mapping.mappingdemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
