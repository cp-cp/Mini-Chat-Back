package com.example.demo.bean;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Integer questionId;
    private String solver;
}

