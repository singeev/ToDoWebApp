package com.honsu.todo;

import javax.validation.constraints.Size;
import java.util.Date;

public class Todo {
    private int id;
    private String user;

    @Size(min = 6, message = "Enter at least 6 characters!")
    private String desc;
    private Date targetDate;
    private boolean isDone;

    public Todo(){}

    public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "ToString - Todo [" +
                "id = " + id +
                ", user = " + user +
                ", desc = " + desc +
                ", targetDate = " + targetDate +
                ", isDone = " + isDone +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;

        Todo todo = (Todo) o;

        return id == todo.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
