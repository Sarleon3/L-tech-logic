package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "userPassword")
    private String userPassword;

    public Users() {

    }

    public Users(String userName, String email, String userPassword) {
        super();
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getuserName() {
        return userName;
    }
    public void setuserName(String userName) {
        this.userName = userName;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public String getuserPassword() {
        return userPassword;
    }
    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}