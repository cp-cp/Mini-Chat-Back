package com.example.demo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CountController {

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/actuator/metrics/tomcat.global.current.connections")
    public ResponseEntity<?> getCurrentConnections() {
        Counter connectionsCounter = meterRegistry.counter("tomcat.global.current.connections");

        return ResponseEntity.ok(meterRegistry.find("tomcat.global.current.connections").counter().count());
    }
    @GetMapping("/connections")
    public String getTheCurrentConnections() {
        Counter counter = meterRegistry.counter("tomcat.global.current.connections");
        return "Current connections: " + counter.count();
    }
//    @Autowired
//    private WebServerFactory webServer;
//
//    @GetMapping("/connections")
//    public String getCurrentConnectionss() {
//        TomcatWebServer tomcatWebServer = (TomcatWebServer) webServer;
//        long currentConnections = tomcatWebServer.getTomcat().getConnector().getProtocolHandler().awaitConnectionsClose();
//        return "Current connections: " + currentConnections;
//    }


}
