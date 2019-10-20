package com.trial.mobilethreatdefence.Models;

public class HomeModel {
    String head;
    String description;

    public HomeModel(String head, String description) {
        this.head = head;
        this.description = description;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
