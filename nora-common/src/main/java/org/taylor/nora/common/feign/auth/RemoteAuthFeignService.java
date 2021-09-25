package org.taylor.nora.common.feign.auth;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nora-auth", fallback = RemoteAuhFallbackService.class)
public interface RemoteAuthFeignService {


    @GetMapping("hello")
    String hello();

}
