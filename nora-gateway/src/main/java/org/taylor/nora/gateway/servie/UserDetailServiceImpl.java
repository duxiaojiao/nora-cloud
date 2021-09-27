package org.taylor.nora.gateway.servie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.taylor.nora.common.feign.auth.RemoteAuthFeignService;
import org.taylor.nora.common.feign.auth.dto.UserInfoDTO;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@Slf4j
public class UserDetailServiceImpl implements ReactiveUserDetailsService {

    @Resource
    private RemoteAuthFeignService remoteAuthFeignService;

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        /*定义权限集合*/
        List<GrantedAuthority> authority = new ArrayList<>();
        SimpleGrantedAuthority role_seller = new SimpleGrantedAuthority("ROLE_USER");
        authority.add(role_seller);
        if (username == null) {
            return null;
        }
        // WebFlux异步调用，同步会报错
        Future<UserInfoDTO> future = executorService.submit(() ->remoteAuthFeignService.getUserInfoByName(username));
        UserInfoDTO userInfo = new UserInfoDTO();
        try {
             userInfo = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(),e);
        }
        if(userInfo != null){
            if (userInfo.getUsername().equals(username)) {
                UserDetails user = User.withUsername(userInfo.getUsername())
                        .password(userInfo.getPassword())
                        .roles("USER")
                        .build();
                return Mono.just(user);
            }
        }
        return Mono.error(new UsernameNotFoundException("User Not Found"));
    }
}
