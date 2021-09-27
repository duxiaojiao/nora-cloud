package org.taylor.nora.auth.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taylor.nora.common.feign.auth.dto.UserInfoDTO;

@RestController
@RequestMapping("user")
public class UserController {


    @GetMapping("info")
    public UserInfoDTO getUserInfoByName(@RequestParam String userName) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername(userName);
        userInfoDTO.setPassword("123456");
        return userInfoDTO;
    }


}
