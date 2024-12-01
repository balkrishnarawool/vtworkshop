package com.balarawool.vtworkshop.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadsController {

    @GetMapping("/thread")
    public ResponseEntity<String> getThread() {
        return ResponseEntity.ok(Thread.currentThread().toString());
    }
}
