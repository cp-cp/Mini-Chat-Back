package com.example.demo.service;

import com.example.demo.bean.Question;
import com.example.demo.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Question> findByContent(String content) {
        return questionRepository.findByContent(content);
    }
    public List<Question> findByTitle(String content) {
        return questionRepository.findByTitle(content);
    }

    public void deleteById(Integer id) {
         questionRepository.deleteById(id);
    }

    public void addQuest(Question question) {
        questionRepository.save(question);
    }

}
