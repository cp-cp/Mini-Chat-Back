package com.example.demo.dao;

import com.example.demo.bean.KeyWord;
import com.example.demo.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyWordRepository extends JpaRepository<KeyWord,Integer> {
}
