package com.dmn.healthassistant.model;

public class Userinfo {
    private String id;

    private String nickname;
    private Integer gender;
    private String city;
    private String username;
    private String email;
    private String phone;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public Integer getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }
    public Userinfo(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Userinfo(String id, String nickname, String username, Integer gender) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.gender = gender;
    }
}
