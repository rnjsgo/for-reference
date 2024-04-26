package com.thecommerce.app.domain.user.service;

import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.exception.AlreadyExistNicknameException;
import com.thecommerce.app.domain.user.exception.AlreadyExistUserIdException;
import com.thecommerce.app.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(final UserSignUpDto userSignUpDto) {
        validateUserIdDuplication(userSignUpDto.getUserId());
        validateNicknameDuplication(userSignUpDto.getNickname());

        userRepository.save(userSignUpDto.toEntity(userSignUpDto));
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
}
