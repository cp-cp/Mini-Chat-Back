package com.example.demo.dao;

import com.example.demo.bean.Answer;
import com.example.demo.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    //List<Question> findByQuest(String quest);
}