package com.ideationstorm.com.ideationstorm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public @ResponseBody List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/user/{id}")
    public String getUserByUsername(@RequestParam String username, @RequestParam(required = false) int id) {
        System.out.println(id);
        User user = userRepository.findByUsername(username);

        return  user.toString();
    }
}
