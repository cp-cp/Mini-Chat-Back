package com.example.demo.service;

import com.example.demo.bean.KeyWord;
import com.example.demo.bean.Question;
import com.example.demo.dao.KeyWordRepository;
import com.example.demo.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private KeyWordRepository keyWordRepository;

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

    public void addQuest(Question question)
    {
//        if (question.getKeyWords() == null) {
//            question.setKeyWords()= new ArrayList<>();
//        }
        List<String> ls=processString(question.getContent());
        for (String l : ls) {
            KeyWord keyWord=new KeyWord();
            keyWord.setWords(l);
            System.out.println(keyWord.getWords());
            keyWordRepository.save(keyWord);
            question.getKeyWords().add(keyWord);
        }
        questionRepository.save(question);
    }

    public List<String> processString(String input) {
        String output = "";
        List<String> ls=new ArrayList<>();
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "F:\\pythonProject\\chatbot\\main.py", input);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                ls.add(line);
            }

            p.waitFor();
            in.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ls;
    }
}
