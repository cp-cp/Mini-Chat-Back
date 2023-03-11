package com.example.demo.service;

import com.example.demo.bean.Question;
import com.example.demo.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public List<Question> findAll()
    {
        return questionRepository.findAll();
    }

    public List<Question> findByQuest(String quest) {
        return null;//questionRepository.findByQuest(quest);
    }
    public Optional<Question> findById(Integer id){return questionRepository.findById(id);}
}
