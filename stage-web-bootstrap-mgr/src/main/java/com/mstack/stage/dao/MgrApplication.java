package com.mstack.stage.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MgrApplication {

    public static void main(String[] args) {
        log.info("MgrApplication begin...");
        try {
            SpringApplication.run(MgrApplication.class, args);
            log.info("MgrApplication run sucessful!");
        } catch (Throwable throwable) {
            log.error("MgrApplication run error!error msg is {}", throwable.getMessage(), throwable);
        }
    }
}
