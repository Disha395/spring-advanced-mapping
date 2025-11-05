package com.mapping.mappingdemo.dao;

import com.mapping.mappingdemo.entity.Instructor;
import com.mapping.mappingdemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailsById(int id);

}