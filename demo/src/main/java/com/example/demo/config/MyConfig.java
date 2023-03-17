//package com.example.demo.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Configuration
//public class MyConfig {
//
//    @Bean
//    public HikariDataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springboot");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456");
//        dataSource.setMaximumPoolSize(1); // 设置最大连接数为5
//        return dataSource;
//    }
//
//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/login");
//            }
//        };
//    }
//
//    private static class MyInterceptor implements HandlerInterceptor {
//        private AtomicInteger connectionCount = new AtomicInteger(0);
//
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//                throws Exception {
//            if (connectionCount.incrementAndGet() > 5) { // 如果连接数超过5则拒绝请求
//                response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "连接数过多，请稍后再试");
//                return false;
//            }
//            return true;
//        }
//
//        @Override
//        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//                throws Exception {
//            connectionCount.decrementAndGet(); // 处理完请求后将连接数减1
//        }
//    }
//}
