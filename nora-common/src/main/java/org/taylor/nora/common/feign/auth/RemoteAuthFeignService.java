package org.taylor.nora.common.feign.auth;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taylor.nora.common.feign.auth.dto.UserInfoDTO;

@FeignClient(value = "nora-auth", fallback = RemoteAuhFallbackService.class)
public interface RemoteAuthFeignService {


    @GetMapping("hello")
    String hello();

    @GetMapping("user/info")
    UserInfoDTO getUserInfoByName(@RequestParam String userName);


}
