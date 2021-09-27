package org.taylor.nora.auth.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @GetMapping
    public String hello(HttpServletRequest request){
        Object userDto = request.getSession().getAttribute("userDto");
        log.info("获取用户信息:{}",userDto);
        return "Nora电商系统";
    }
}
