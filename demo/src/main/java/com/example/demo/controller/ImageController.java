package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bean.Answer;
import com.example.demo.bean.Question;
import com.example.demo.dao.AnswerRepository;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

//package com.example.demo.controller;
//
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//@Controller
//@CrossOrigin
//@RequestMapping("/image")
//public class ImageController {
//
////    @RequestMapping(value = "/get", method = RequestMethod.GET)
////    @ResponseBody
////    @GetMapping("/get")
//@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "image/jpeg")
//public void getImage(HttpServletResponse response) throws IOException {
//        // 读取图片文件
//        File file = new File("F:\\pythonProject\\chatbot\\wordcloud.png");
//        FileInputStream fis = new FileInputStream(file);
//
//        // 设置响应头
//        response.setContentType("image/jpeg");
//        response.setContentLength((int)file.length());
//
//        // 将图片写入响应流
//        ServletOutputStream out = response.getOutputStream();
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = fis.read(buffer)) > 0) {
//            out.write(buffer, 0, len);
//        }
//        out.flush();
//        out.close();
//        fis.close();
//    }
//}
@RestController
@RequestMapping("/api")
public class ImageController {

        @GetMapping("/image")
        public ResponseEntity<byte[]> getImage() throws IOException {
                File file = new File("F:\\pythonProject\\chatbot\\wordcloud.png");
                FileInputStream fis = new FileInputStream(file);

                byte[] bytes = new byte[(int)file.length()];
                fis.read(bytes);
                fis.close();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);

                return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        }
}
