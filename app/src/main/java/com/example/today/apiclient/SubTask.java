package com.example.today.apiclient;

import com.google.gson.annotations.SerializedName;

public class SubTask {
    @SerializedName("id")
    int id;
    @SerializedName("key")
    int key;
    @SerializedName("subtask")
    String value;

    public SubTask(int id, int key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
