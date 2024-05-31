package com.ether.data.dto;

import java.util.LinkedList;
import java.util.List;

public class TopicOperate {

    private List<Topic> topic = new LinkedList<>();
    private String[] operate;

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    public String[] getOperate() {
        return operate;
    }

    public void setOperate(String[] operate) {
        this.operate = operate;
    }
}