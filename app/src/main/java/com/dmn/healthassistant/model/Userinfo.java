package com.dmn.healthassistant.model;

public class Userinfo {
    private String username;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public Userinfo(String username, String id) {
        this.id = id;
        this.username = username;
    }
}
