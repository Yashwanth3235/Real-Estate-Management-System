package com.example.rse.service;

import com.example.rse.model.User;
import com.example.rse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
  
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email); // Make sure you have this method in your repository
//    }


	
}
