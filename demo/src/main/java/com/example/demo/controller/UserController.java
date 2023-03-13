package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController//控制返回的格式，写入Http响应头
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");
        User loginUser = userService.login(username, password);
        System.out.println(loginUser);
        if (loginUser != null) {
            return new ResponseEntity<>("login success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("login fail", HttpStatus.UNAUTHORIZED);
        }
    }
}
