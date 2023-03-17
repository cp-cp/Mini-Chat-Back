package com.example.demo.controller;


import com.example.demo.bean.Question;
import com.example.demo.bean.User;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController//控制返回的格式，写入Http响应头
@CrossOrigin(origins = "*")
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/all")
    List<Question> findAll() {return questionService.findAll();}
    @PostMapping("/findByContent")
    List<Question> findByContent(@RequestBody  String content){
        String newData = content.replaceAll("\"", "");
        System.out.println(newData);
        System.out.println(questionService.findByContent(newData));
        return questionService.findByContent(newData);}
    @PostMapping("/findByTitle")
    List<Question> findByTitle(@RequestBody  String title){
        String newData = title.replaceAll("\"", "");
        System.out.println(newData);
//        System.out.println(questionService.findByTitle(newData));
        return questionService.findByTitle(newData);}
    @GetMapping("/getById/{id}")
    Optional<Question> findById(@PathVariable("id") Integer id){return questionService.findById(id);}
    @DeleteMapping("/deleteById/{id}")
    void deleteById(@PathVariable("id") Integer id){questionService.deleteById(id);}

    @PostMapping("/addQuest")
    void addQuest(@RequestBody Question question){questionService.addQuest(question);}
//    @PostMapping("/{questionId}/like/{userId}")
//    public ResponseEntity<String> likeQuestion(@PathVariable int questionId,@PathVariable int userId) {
//        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));
//        User user=
//        if (question.getLikedByUsers().contains(user)) {
//            return ResponseEntity.badRequest().body("User already liked this question");
//        }
//        question.getLikedByUsers().add(user);
//        question.setLikes(question.getLikes() + 1);
//        questionRepository.save(question);
//        return ResponseEntity.ok("Question liked successfully");
//    }
}

