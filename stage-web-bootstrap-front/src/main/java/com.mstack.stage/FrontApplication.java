package com.mstack.stage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FrontApplication {

    public static void main(String[] args) {
        log.info("FrontApplication begin...");
        try {
            SpringApplication.run(FrontApplication.class, args);
            log.info("FrontApplication run sucessful!");
        } catch (Throwable throwable) {
            log.error("FrontApplication run error!error msg is {}", throwable.getMessage(), throwable);
        }
    }
}
