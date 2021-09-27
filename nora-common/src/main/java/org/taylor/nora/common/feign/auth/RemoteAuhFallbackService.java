package org.taylor.nora.common.feign.auth;


import org.springframework.stereotype.Component;
import org.taylor.nora.common.feign.auth.dto.UserInfoDTO;

@Component
public class RemoteAuhFallbackService implements RemoteAuthFeignService {


    @Override
    public String hello() {
        return "测试服务降级";
    }

    @Override
    public UserInfoDTO getUserInfoByName(String userName) {
        return null;
    }
}
