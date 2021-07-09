package com.mstack.stage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@MapperScan(basePackages = {"com.mstack.stage.dao.**"})
//@ComponentScan(basePackages= {"com.mstack.stage.web.**"})
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
