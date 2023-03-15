package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ConnectionInterceptor implements HandlerInterceptor {

    @Autowired
    private HikariDataSource dataSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int activeConnections = dataSource.getHikariPoolMXBean().getActiveConnections();
        if (activeConnections > 3) {
            // 拒绝连接并发出警告
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Too many connections");
            return false;
        }
        return true;
    }
}

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
//        dataSource.setMaximumPoolSize(5); // 设置最大连接数为5
//        return dataSource;
//    }
//
//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/**");
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
//
