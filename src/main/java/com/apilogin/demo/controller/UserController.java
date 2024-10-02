package com.apilogin.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apilogin.demo.dto.LoginDTO;
import com.apilogin.demo.exception.InvalidUserException;
import com.apilogin.demo.model.User;
import com.apilogin.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User registerByUser(@RequestBody User user) {
        try {
            return userService.registerUsers(user);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginByUser(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
            return ResponseEntity.ok(user);
        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<User> findAllUsers() {
        try {
            return userService.findAllUsers();
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public List<User> getUserByUsername(@PathVariable String username) {
        try {
            return userService.getByUsername(username);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        try {
            return userService.getByEmail(email);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/instituicao/{instituicaoParceira}")
    public List<User> getUserByInstituicao(@PathVariable String instituicaoParceira) {
        try {
            return userService.getByInstituicao(instituicaoParceira);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/proposito/{propositoFinal}")
    public List<User> getUserByProposito(@PathVariable String propositoFinal) {
        try {
            return userService.getByProposito(propositoFinal);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable Long id,@RequestBody User userDetails) {
        try {
            return userService.updateUser(id, userDetails);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/name/{username}")
    public void deleteUserByUsername(@PathVariable String username) {
        try {
            userService.deleteByName(username);
        } catch (InvalidUserException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
