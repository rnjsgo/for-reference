package com.thecommerce.app.domain.user.dto.request;

import com.thecommerce.app.domain.user.entity.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSignUpDto {

    @NotBlank(message = "사용자 ID는 비어 있을 수 없습니다")
    private String userId;

    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 설정해야 합니다")
    private String password;

    @NotBlank(message = "닉네임은 비어 있을 수 없습니다")
    private String nickname;

    @NotBlank(message = "이름은 비어 있을 수 없습니다")
    private String name;

    @NotBlank(message = "전화번호는 비어 있을 수 없습니다")
    @Pattern(regexp = "^\\d{10,11}$", message = "전화번호는 10자리 또는 11자리 숫자여야 합니다")
    private String phoneNumber;

    @NotBlank(message = "이메일 주소는 비어 있을 수 없습니다")
    @Email(message = "유효한 이메일 주소여야 합니다")
    private String email;

    public User toEntity(UserSignUpDto userSignUpDto) {
        return User.builder()
                .userId(userSignUpDto.getUserId())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .name(userSignUpDto.getName())
                .phoneNumber(userSignUpDto.getPhoneNumber())
                .email(userSignUpDto.getEmail())
                .build();
    }
}
