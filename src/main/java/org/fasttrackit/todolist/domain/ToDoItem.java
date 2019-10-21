package org.fasttrackit.todolist.domain;
//using a newer package that is also compatible with SQL (with a bit of code) and working better than the other packages

import java.time.LocalDate;

public class ToDoItem {
    //recommended long for id as thr increment will get very long if the app is successful
    private long id;
    private String description;
    private LocalDate deadline;
    private boolean done;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }
}
