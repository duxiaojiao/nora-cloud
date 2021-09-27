package org.taylor.nora.job.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taylor.nora.common.feign.auth.RemoteAuthFeignService;

import javax.annotation.Resource;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource
    private RemoteAuthFeignService remoteAuthFeignService;


    @GetMapping
    public String hello(){
        return remoteAuthFeignService.hello();
    }

}
