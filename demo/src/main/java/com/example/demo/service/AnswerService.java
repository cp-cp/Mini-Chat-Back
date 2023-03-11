package com.example.demo.service;

import com.example.demo.bean.Answer;
import com.example.demo.bean.Question;
import com.example.demo.dao.AnswerRepository;
import com.example.demo.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> findAll()
    {
        return answerRepository.findAll();
    }
}
