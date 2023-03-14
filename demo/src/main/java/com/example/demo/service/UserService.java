package com.example.demo.service;

import com.example.demo.bean.Question;
import com.example.demo.bean.User;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

//    public List<Question> getLikedQuestions(Long userId) {
//            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//            return user.getLikedQuestions();
//    }

    public List<User> all() {
        return userRepository.findAll();
    }

    public List<Question> getLikedQuestions(int userId) {
        return userRepository.findById(userId).get().getLikedQuestions();
    }

    public List<Question> askedQuestions(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("没有对象");
            return null;
        } else {
            String name = user.get().getUsername();
            return questionRepository.findByAsker(name);
        }
    }
}
