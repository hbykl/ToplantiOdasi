package com.example.toplantiOdasi.Services;

import com.example.toplantiOdasi.Classes.User;
import com.example.toplantiOdasi.Repositorys.UserRepository;
import com.example.toplantiOdasi.ServiceImpl.UserImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserImpl {
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User getUser(String mail, String password) {
        return userRepository.findByMailAndPassword(mail,password).orElse(null);

    }
    @Override
    public boolean checkGetUser(User user){
        return user!=null;
    }
}
