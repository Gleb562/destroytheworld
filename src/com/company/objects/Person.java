package com.company.objects;

public class Person {
    private int id;
    private String username;
    private String password;
    private int accessLevel;


    public Person(int id, String username, String password,int accessLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String pname) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
