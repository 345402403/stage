package com.mstack.stage.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limeng429
 * @datae 2021-06-28
 */
@RestController
@RequestMapping("/stage")
public class DemoController {


    @GetMapping(value = "/person")
    public String loginPage(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        return "login";
    }
}
