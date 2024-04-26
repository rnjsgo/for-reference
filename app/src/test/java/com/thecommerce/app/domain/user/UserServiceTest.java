package com.thecommerce.app.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.exception.AlreadyExistNicknameException;
import com.thecommerce.app.domain.user.exception.AlreadyExistUserIdException;
import com.thecommerce.app.domain.user.repository.UserRepository;
import com.thecommerce.app.domain.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Nested
    class 회원가입 {

        @Test
        public void 회원가입_성공() {
            // given
            UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                    .userId("newUser")
                    .password("password")
                    .nickname("newNickname")
                    .name("New User")
                    .phoneNumber("01012345678")
                    .email("user01@example.com")
                    .build();
            // stub
            when(userRepository.findByUserId("newUser")).thenReturn(Optional.empty());
            when(userRepository.findByNickname("newNickname")).thenReturn(Optional.empty());

            // when
            userService.signUp(userSignUpDto);

            // then
            verify(userRepository).save(any(User.class));
        }

        @Test
        public void 회원가입_실패_유저아이디_중복() {
            // given
            UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                    .userId("existUser")
                    .password("password")
                    .nickname("existNickname")
                    .name("Exist User")
                    .phoneNumber("01012345678")
                    .email("user@example.com)")
                    .build();

            // stub
            when(userRepository.findByUserId(userSignUpDto.getUserId())).thenReturn(
                    Optional.of(User.builder().build()));

            // when
            assertThatThrownBy(() -> userService.signUp(userSignUpDto))
                    .isInstanceOf(AlreadyExistUserIdException.class);
        }

        @Test
        public void 회원가입_실패_닉네임_중복() {
            // given
            UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                    .userId("existUser")
                    .password("password")
                    .nickname("existNickname")
                    .name("Exist User")
                    .phoneNumber("01012345678")
                    .email("user@example.com)")
                    .build();

            // stub
            when(userRepository.findByUserId(userSignUpDto.getUserId())).thenReturn(
                    Optional.empty());
            when(userRepository.findByNickname(userSignUpDto.getNickname())).thenReturn(
                    Optional.of(User.builder().build()));

            // when
            assertThatThrownBy(() -> userService.signUp(userSignUpDto))
                    .isInstanceOf(AlreadyExistNicknameException.class);
        }
    }
}
