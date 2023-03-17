package com.example.demo.controller;

import com.example.demo.bean.JsonData;
import com.example.demo.bean.User;
import com.example.demo.dao.JsonRepository;
import com.example.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/history")
public class JsonController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonRepository jsonRepository;

    @PostMapping("/store/{userid}")
    public ResponseEntity<String> storeJson(@PathVariable int userid, @RequestBody List<String> jsonList) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("No user");
        }
        for (int i = 0; i < jsonList.size(); i += 2) {
            String question = jsonList.get(i);
            String answer = jsonList.get(i+1);
            JsonData jsonData = new JsonData(question, answer);
            jsonRepository.save(jsonData);
            user.get().getJsonData().add(jsonData);
        }
        System.out.println("123123312312321213132132");
        userRepository.save(user.get());
        return ResponseEntity.ok("success");
    }


    @GetMapping("/all/{userId}")
    public List<JsonData> getAllJsonData(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
        {
            return null;
        }
        else
        {
            return user.get().getJsonData();
        }
    }
}
