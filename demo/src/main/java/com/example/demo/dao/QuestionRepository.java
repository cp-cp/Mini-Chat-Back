package com.example.demo.dao;

import com.example.demo.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByContent(String content);

    List<Question> findByTitle(String content);

    List<Question> findByAsker(String name);
    //List<Question> findByQuest(String quest);
}