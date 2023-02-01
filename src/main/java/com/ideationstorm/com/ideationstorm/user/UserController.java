package com.ideationstorm.com.ideationstorm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsers();
    }
//    @GetMapping("/users/{username}")
//    public String getUserByUsername(@RequestParam String username, @RequestParam(required = false) String ) {
//        System.out.println(id);
//        User user = userService.loadUserByUsername(username);
//        return  user.toString();
//    }
}
