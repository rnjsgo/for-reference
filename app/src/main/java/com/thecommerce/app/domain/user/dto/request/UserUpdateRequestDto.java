package com.thecommerce.app.domain.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

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
}
