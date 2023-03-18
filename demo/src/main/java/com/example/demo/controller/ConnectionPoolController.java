package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class ConnectionPoolController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/connection-pool/active-connections")
    public int getActiveConnections() throws SQLException {
        return dataSource.getConnection().getMetaData().getMaxConnections();
    }
}
