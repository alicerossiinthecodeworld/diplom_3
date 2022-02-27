package com.model;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public static User getRandomUser(){
        User user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10)+"@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        user.setName(RandomStringUtils.randomAlphabetic(10));
        return user;
    }
}
