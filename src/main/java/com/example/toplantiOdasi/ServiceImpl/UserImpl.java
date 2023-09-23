package com.example.toplantiOdasi.ServiceImpl;

import com.example.toplantiOdasi.Classes.User;

import java.util.List;

public interface UserImpl {
    List<User> getAll();
    void saveUser(User user);

    User getUser(String mail,String password);
    boolean checkGetUser(User user);
}
