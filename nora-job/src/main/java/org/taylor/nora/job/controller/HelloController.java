package org.taylor.nora.job.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taylor.nora.common.feign.auth.RemoteAuthFeignService;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private RemoteAuthFeignService remoteAuthFeignService;


    @GetMapping
    public String hello(){
        return remoteAuthFeignService.hello();
    }

}
