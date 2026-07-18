package com.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.dto.Task;

public class TaskDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harshad");

    public void insertTask(Task task) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(task);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Task fetchById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public List<Task> fetchAllTasks() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select t from Task t order by t.id desc");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void updateTaskStatus(int id, boolean currentStatus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Task task = em.find(Task.class, id);
            if (task != null) {
                task.setCompleted(!currentStatus);
                em.merge(task);
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteTask(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Task task = em.find(Task.class, id);
            if (task != null) {
                em.remove(em.contains(task) ? task : em.merge(task));
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}