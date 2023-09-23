package com.example.toplantiOdasi.Classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Employee")
public class User {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull(message = "*İsim alanı boş bırakılamaz")
    private String name;

    @Column(name = "last_name")
    @NotNull(message = "*Soyad alanı boş bırakılamaz")
    private String lastName;

    @Column(name = "mail")
    @Email(message = "*Yanlış mail formatı")
    @NotNull(message = "*Mail alanı boş bırakılamaz")
    private String mail;

    @Column(name = "password")
    @NotNull(message = "*Şifre alanı boş bırakılamaz")
    private String password;

    public User(){}

    public User(String mail,String password){
        this.mail=mail;
        this.password=password;
    }

    public User(Long id, String name, String lastName, String mail, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public User(String name, String lastName, String mail, String password) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
