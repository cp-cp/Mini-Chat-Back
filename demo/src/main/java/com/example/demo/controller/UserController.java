package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.bean.Question;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@RestController//控制返回的格式，写入Http响应头
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");
        User loginUser = userService.login(username, password);
        Counter counter = meterRegistry.counter("tomcat.global.current.connections");
        System.out.println("Current connections: " + counter.count());
        if (loginUser != null) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", "login success");
            responseMap.put("userId", loginUser.getId());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("login fail", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/likedQuestions/{userId}")
    public List<Question> getLikedQuestions(@PathVariable int userId) {
        return userService.getLikedQuestions(userId);
    }
    @GetMapping("/askedQuestions/{userId}")
    public List<Question> askedQuestions(@PathVariable int userId) {
        return userService.askedQuestions(userId);
    }
    @GetMapping("/addLikedQuestion/{questionId}/{userId}")
    public ResponseEntity<String> addLikedQuestion(@PathVariable int questionId,@PathVariable int userId)
    {
        Optional<Question> question = questionRepository.findById(questionId);
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty())
        {
            return ResponseEntity.badRequest().body("No user");
        }
        else
        {
            if(question.isEmpty())
            {
                return ResponseEntity.badRequest().body("No question");
            }
            else
            {
                Question tquestion=question.get();
                User tuser=user.get();
                if (tquestion.getLikedByUsers().contains(tuser)) {
                    return ResponseEntity.badRequest().body("User already liked this question");
                }
                else{
                    int number= (tquestion.getLikesNumber()!=null?tquestion.getLikesNumber().intValue():0)+1; // 如果integer为空，则返回默认值0
                    tquestion.setLikesNumber(number);
                    tquestion.getLikedByUsers().add(tuser);
                    tuser.getLikedQuestions().add(tquestion);
                    questionRepository.save(tquestion);
                    userRepository.save(tuser);
                    return ResponseEntity.ok("Question liked successfully");
                }
            }
        }
    }
    @GetMapping("/checkLikedQuestion/{questionId}/{userId}")
    public ResponseEntity<?> checkLikedQuestion(@PathVariable("questionId") int questionId, @PathVariable("userId") int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Question> likedQuestions = user.getLikedQuestions();
            boolean liked = false;
            for (Question question : likedQuestions) {
                if (question.getId().equals(questionId)) {
                    liked = true;
                    break;
                }
            }
            return ResponseEntity.ok(Map.of("liked", liked));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/deleteLikedQuestion/{questionId}/{userId}")
    public ResponseEntity<String> deleteLikedQuestion(@PathVariable int questionId,@PathVariable int userId)
    {
        Optional<Question> question = questionRepository.findById(questionId);
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty())
        {
            return ResponseEntity.badRequest().body("No user");
        }
        else
        {
            if(question.isEmpty())
            {
                return ResponseEntity.badRequest().body("No question");
            }
            else
            {
                Question tquestion=question.get();
                User tuser=user.get();
                if (!tquestion.getLikedByUsers().contains(tuser)) {
                    return ResponseEntity.badRequest().body("User doesn't like this question");
                }
                else{
                    int number= (tquestion.getLikesNumber()!=null?tquestion.getLikesNumber().intValue():0)-1; // 如果integer为空，则返回默认值0
                    tquestion.setLikesNumber(number);
                    tquestion.getLikedByUsers().remove(tuser);
                    tuser.getLikedQuestions().remove(tquestion);
                    questionRepository.save(tquestion);
                    userRepository.save(tuser);
                    return ResponseEntity.ok("Question unliked successfully"+number);
                }
            }
        }
    }
    @Async
    @Timed(millis = 5000)
    @GetMapping("/resource")
    public ResponseEntity<String> getResource() throws TimeoutException {
        // call third party service
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/all")
    public List<User> all(){
        return userService.all();
    }
}
