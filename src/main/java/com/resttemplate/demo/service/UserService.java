package com.resttemplate.demo.service;

import com.resttemplate.demo.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> getUsers();
    ResponseEntity<String> createUser(User user, String cookie);
    ResponseEntity<String> updateUser(User user, String cookie);
    ResponseEntity<String> deleteUser(Long id, String cookie);

}
