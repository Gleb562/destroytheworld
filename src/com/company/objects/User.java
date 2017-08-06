package com.company.objects;


public class User {

    private int id;
    private String pname;
    private int price;


    public User(int id, String pname, int price) {
        this.id = id;
        this.pname = pname;
        this.price = price;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
