package com.example.toplantiOdasi.Repositorys;

import com.example.toplantiOdasi.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByMailAndPassword(String mail,String password);


}
