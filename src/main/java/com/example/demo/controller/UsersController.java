package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/Users")
public class UsersController {

    @Autowired
    private UsersRepository employeeRepository;

    // get all employees
    @GetMapping("/all")
    public List<Users> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/registration")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        // Проверка на уникальность email
        if (employeeRepository.existsByEmail(users.getemail())) {
            throw new ResourceNotFoundException("Email is already in use: " + users.getemail());
        }

        // Сохранение нового пользователя, если email уникален
        Users savedUser = employeeRepository.save(users);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Users> authenticateEmployee(@RequestParam String email, @RequestParam String password) {

        // Попытка найти по email
        Users users = employeeRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with email or username: " + email));

        // Проверка пароля
        if (!users.getuserPassword().equals(password)) {
            throw new UnauthorizedException("Incorrect password for " + email);
        }

        return ResponseEntity.ok(users);
    }
}