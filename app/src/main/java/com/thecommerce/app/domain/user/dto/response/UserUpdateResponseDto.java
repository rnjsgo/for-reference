package com.thecommerce.app.domain.user.dto.response;

import com.thecommerce.app.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdateResponseDto {

    private String userId;
    private String nickname;
    private String name;
    private String phoneNumber;
    private String email;

    public static UserUpdateResponseDto fromEntity(final User user) {
        return UserUpdateResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
