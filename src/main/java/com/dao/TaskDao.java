package com.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dto.Task;

public class TaskDao {

    // Tu banavlela exact JPA configuration setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harshad");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
    
    // 1. Task Add karne
    public void insertTask(Task task) {
        et.begin();
        em.persist(task);
        et.commit();
    }

    // 2. ID varun single task shodhane
    public Task fetchById(int id) {
        return em.find(Task.class, id);
    }

    // 3. Sagle Tasks list sathi baher kadhne
    public List<Task> fetchAllTasks() {
        Query q = em.createQuery("select t from Task t order by t.id desc");
        List<Task> list = q.getResultList();
        return list;
    }

    // 4. Task status swap karne (Done/Undone)
    public void updateTaskStatus(int id, boolean currentStatus) {
        Task task = fetchById(id);
        if(task != null) {
            task.setCompleted(!currentStatus);
            et.begin();
            em.merge(task);
            et.commit();
        }
    }

    // 5. Task Delete karne
    public void deleteTask(int id) {
        Task task = fetchById(id);
        if(task != null) {
            et.begin();
            em.remove(task);
            et.commit();
        }
    }
}