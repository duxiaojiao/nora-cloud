package org.taylor.nora.common.feign.auth.dto;

import lombok.Data;

@Data
public class UserInfoDTO {

    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;
}
