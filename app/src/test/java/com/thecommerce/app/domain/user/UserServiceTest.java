package com.thecommerce.app.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.response.UserListDto;
import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.exception.AlreadyExistNicknameException;
import com.thecommerce.app.domain.user.exception.AlreadyExistUserIdException;
import com.thecommerce.app.domain.user.repository.UserRepository;
import com.thecommerce.app.domain.user.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


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

            // then
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

            // then
            assertThatThrownBy(() -> userService.signUp(userSignUpDto))
                    .isInstanceOf(AlreadyExistNicknameException.class);
        }
    }

    @Nested
    class 회원목록조회 {

        @Test
        public void 회원목록조회_성공() {

            // given
            User user1 = User.builder().userId("user01").build();
            User user2 = User.builder().userId("user02").build();

            List<User> userList = Arrays.asList(user1, user2);
            Page<User> page = new PageImpl<>(userList);
            Pageable pageable = Pageable.unpaged();

            // stub
            when(userRepository.findAll(pageable)).thenReturn(page);

            // when
            UserListDto result = userService.getUsers(pageable);

            // then
            assertNotNull(result);
            assertEquals(2, result.getUsers().size());
            assertEquals("user01", result.getUsers().get(0).getUserId());
            verify(userRepository).findAll(pageable);
        }

        @Test
        void 회원목록조회_실패_잘못된페이지형식() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> userService.getUsers(Pageable.ofSize(-1)));

            System.out.println("IllegalArgumentException message : " + exception.getMessage());
        }
    }
}