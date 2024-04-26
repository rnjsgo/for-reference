package com.thecommerce.app.domain.user.controller;

import com.thecommerce.app.common.util.Response;
import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.request.UserUpdateRequestDto;
import com.thecommerce.app.domain.user.dto.response.UserListDto;
import com.thecommerce.app.domain.user.dto.response.UserUpdateResponseDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserApiInterface {

    @PostMapping("/join")
    public ResponseEntity<Response> signUp(@Valid @RequestBody final UserSignUpDto userSignUpDto,
            Errors errors) {
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<Response<UserListDto>> getUsers(
            @PageableDefault(size = 10, sort = "name") final Pageable pageable) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<UserUpdateResponseDto>> updateUser(@PathVariable final int id,
            @Valid @RequestBody final UserUpdateRequestDto userUpdateRequestDto,
            Errors errors) {
        return null;
    }
}
