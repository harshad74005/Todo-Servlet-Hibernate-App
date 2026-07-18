package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;

    // Constructors
    public Task() {}

    public Task(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }public boolean isCompleted() {
        return isCompleted;
    }
    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", isCompleted=" + isCompleted + "]";
    }
}