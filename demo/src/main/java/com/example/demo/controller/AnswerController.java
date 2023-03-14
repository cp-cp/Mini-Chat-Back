package com.example.demo.controller;


import com.example.demo.bean.Answer;
import com.example.demo.bean.Question;
import com.example.demo.dao.AnswerRepository;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;



@RestController//控制返回的格式，写入Http响应头
@CrossOrigin(origins = "*")
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionRepository questionRepository;
    @GetMapping("/all")
    List<Answer> findAll() {
        return answerService.findAll();
    }

    @GetMapping("/init")
    List<Answer> init() {
        System.out.print("111111111111");
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Answer answer = new Answer();
            answer.setContent(RandomStringUtils.randomAlphanumeric(20));
            answer.setQuestionId(random.nextInt(3));
            answerRepository.save(answer);
        }
        return findAll();
    }

    @PostMapping("/addAns")
    void addQuest(@RequestBody Answer answer){answerService.addAns(answer);}

//    @GetMapping("/{quest}")
//    List<Question> findByNumber(@PathVariable("quest") String quest){return questionService.findByQuest(quest);}
}

