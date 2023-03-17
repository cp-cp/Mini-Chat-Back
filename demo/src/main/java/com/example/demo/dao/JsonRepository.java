package com.example.demo.dao;

import com.example.demo.bean.JsonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRepository extends JpaRepository<JsonData,Integer> {

}
