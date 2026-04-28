package com.todolist.app;

public class Task {
    private int id;
    private String title;
    private boolean completed;
    private String priority;

    public Task(int id, String title, boolean completed, String priority) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public String getPriority() { return priority; }

    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setTitle(String title) { this.title = title; }
}
