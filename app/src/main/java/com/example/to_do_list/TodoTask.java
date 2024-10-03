package com.example.to_do_list;

import java.io.Serializable;

public class TodoTask implements Serializable {
    private String title;
    private String description;

    public TodoTask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
