package com.example.demo.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "json_data")
public class JsonData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;
    private String answer;

    public JsonData(String question, String answer) {
        this.answer=answer;
        this.question=question;
    }
    public JsonData() {
        // 空的无参构造函数
    }
}