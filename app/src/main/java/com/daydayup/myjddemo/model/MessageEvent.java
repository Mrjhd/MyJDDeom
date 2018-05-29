package com.daydayup.myjddemo.model;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class MessageEvent {
    private String pid;

    public MessageEvent(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
