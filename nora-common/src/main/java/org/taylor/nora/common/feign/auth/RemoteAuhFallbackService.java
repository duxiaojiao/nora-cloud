package org.taylor.nora.common.feign.auth;


import org.springframework.stereotype.Component;

@Component
public class RemoteAuhFallbackService implements RemoteAuthFeignService {


    @Override
    public String hello() {
        return "测试服务降级";
    }
}
