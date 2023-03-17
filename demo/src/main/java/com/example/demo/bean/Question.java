package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String asker;
    private Integer likesNumber=0;
    @OneToMany(mappedBy = "questionId")
    private List<Answer> answers;
//    @OneToMany
    @ManyToMany(mappedBy = "likedQuestions")
    @JsonIgnore
    private List<User> likedByUsers;

    @OneToMany
    List<KeyWord>keyWords=new ArrayList<>();
//
//    @JsonIgnore
//    private List<User> likedByUsers;
}

