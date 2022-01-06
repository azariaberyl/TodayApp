package com.example.today.apiclient;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("desc")
    String desc = "";
    ArrayList<SubTask> subTask= new ArrayList<>();

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
    public Task( String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<SubTask> getSubTask() {
        return subTask;
    }

    public void addSubTask(int id, int key, String value) {
        subTask.add(new SubTask(id,key,value));
    }
    public void addSubTask(int id, int key) {
        subTask.add(new SubTask(id,key,""));
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", subTask=" + subTask +
                '}';
    }
}
