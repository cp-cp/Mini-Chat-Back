package com.example.demo.controller;


import com.example.demo.bean.Question;
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
@CrossOrigin
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/all")
    List<Question> findAll() {
        return questionService.findAll();
    }
    @GetMapping("/{quest}")
    List<Question> findByNumber(@PathVariable("quest") String quest){return questionService.findByQuest(quest);}
    @GetMapping("/{id}")
    Optional<Question> findById(@PathVariable("id") Integer id){return questionService.findById(id);}
}

