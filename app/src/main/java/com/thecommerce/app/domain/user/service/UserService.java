package com.thecommerce.app.domain.user.service;

import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.request.UserUpdateRequestDto;
import com.thecommerce.app.domain.user.dto.response.UserListDto;
import com.thecommerce.app.domain.user.dto.response.UserUpdateResponseDto;
import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.exception.AlreadyExistNicknameException;
import com.thecommerce.app.domain.user.exception.AlreadyExistUserIdException;
import com.thecommerce.app.domain.user.exception.UserNotFoundException;
import com.thecommerce.app.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public void signUp(final UserSignUpDto userSignUpDto) {
        validateUserIdDuplication(userSignUpDto.getUserId());
        validateNicknameDuplication(userSignUpDto.getNickname());

        userRepository.save(userSignUpDto.toEntity(userSignUpDto));
    }

    // 회원 목록 조회
    public UserListDto getUsers(final Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        UserListDto userListDto = UserListDto.of(users);

        return userListDto;
    }

    // 회원 정보 수정
    @Transactional
    public UserUpdateResponseDto updateUser(final Long id,
            final UserUpdateRequestDto userUpdateRequestDto) {

        // 존재하는 회원인지 확인
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        // 닉네임 중복 확인 (본인 닉네임 제외)
        validateNicknameDuplication(id, userUpdateRequestDto.getNickname());

        // 회원 정보 수정
        user.updateInfo(userUpdateRequestDto);

        return UserUpdateResponseDto.fromEntity(user);
    }

    private void validateUserIdDuplication(final String userId) {
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new AlreadyExistUserIdException();
        }
    }


    private void validateNicknameDuplication(final String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new AlreadyExistNicknameException();
        }
    }

    private void validateNicknameDuplication(final Long id, final String nickname) {
        if (userRepository.findByNicknameAndIdNot(nickname, id).isPresent()) {
            throw new AlreadyExistNicknameException();
        }
    }
}
