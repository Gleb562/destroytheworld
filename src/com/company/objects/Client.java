package com.company.objects;


public class Client {

    private int id;
    private String email;
    private String phone;
    private String clientName;



    public Client(int id,String email, String phone, String clientName) {
        this.id = id;
        this.email = email;
        this.clientName = clientName;
        this.phone = phone;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

