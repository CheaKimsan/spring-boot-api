package com.setec.springdb.service;

import com.setec.springdb.model.Role;
import com.setec.springdb.model.User;
import com.setec.springdb.repository.RoleRepository;
import com.setec.springdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() { return repository.findAll(); }

    public User getUserById(Long id) { return repository.findById(id).orElse(null); }

    public User saveUser(User user) { return repository.save(user); }

    public void deleteUser(Long id) { repository.deleteById(id); }

    public User assignRole(Long userId, Long roleId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        user.setRole(role);
        return repository.save(user);
    }
}