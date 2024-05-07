package com.springbootproject.service;

import com.springbootproject.object.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceManualSqlService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Student geStudentByIdWithSqlQuery(int id) {
        Query jpqlQuery = getEntityManager().createQuery("SELECT s FROM Student s WHERE s.courseDtoId=:courseDtoId");
        jpqlQuery.setParameter("id", id);
        return (Student) jpqlQuery.getSingleResult();
    }

}
