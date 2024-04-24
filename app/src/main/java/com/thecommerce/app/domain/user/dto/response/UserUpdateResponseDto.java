package com.thecommerce.app.domain.user.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateResponseDto {

    private String userId;
    private String nickname;
    private String name;
    private String phoneNumber;
    private String email;
}
