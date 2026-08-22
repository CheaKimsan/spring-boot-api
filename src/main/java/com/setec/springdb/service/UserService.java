package com.setec.springdb.service;

import com.setec.springdb.model.Student;
import com.setec.springdb.model.User;
import com.setec.springdb.repository.StudentRepository;
import com.setec.springdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired

    private UserRepository repository;

    public List<User> getAllUsers() { return repository.findAll(); }

    public User getUserById(Long id) { return repository.findById(id).orElse(null); }

    public User saveUser(User user) { return repository.save(user); }

    public void deleteUser(Long id) { repository.deleteById(id); }

}
