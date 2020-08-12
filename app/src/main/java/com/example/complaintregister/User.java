package com.example.complaintregister;

public class User {
    private String Name;
    private String Email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
