package com.l4gger.server_virtual_threads.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoServiceController {

    @GetMapping("demo-service")
    public void demoService() throws InterruptedException {
        Thread.sleep(3000);
        log.info("Blocking for 3 seconds");
    }
}
