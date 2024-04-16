package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class Discussions {
    private String topic;
    private List<String> messages;

    public Discussions(String topic) {
        this.topic = topic;
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}