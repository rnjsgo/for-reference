package com.thecommerce.app.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecommerce.app.domain.user.controller.UserController;
import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.request.UserUpdateRequestDto;
import com.thecommerce.app.domain.user.dto.response.UserListDto;
import com.thecommerce.app.domain.user.dto.response.UserUpdateResponseDto;
import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.service.UserService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper om = new ObjectMapper();


    // ("/api/user/join")
    @Test
    public void 회원가입() throws Exception {
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .userId("newUser")
                .password("password")
                .nickname("newNickname")
                .name("New User")
                .phoneNumber("01012345678")
                .email("uew01@example@.com")
                .build();

        mockMvc.perform(post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(userSignUpDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ("/api/user/list")
    @Test
    public void 회원목록조회() throws Exception {
        // given
        User user1 = User.builder()
                .userId("user01")
                .password("password01")
                .nickname("nick01")
                .name("User One")
                .phoneNumber("01010000001")
                .email("user01@example.com")
                .build();
        User user2 = User.builder()
                .userId("user02")
                .password("password02")
                .nickname("nick02")
                .name("User Two")
                .phoneNumber("01010000002")
                .email("user02@example.com")
                .build();

        Page<User> users = new PageImpl<>(Arrays.asList(user1, user2));
        UserListDto userListDto = UserListDto.of(users);

        // stub
        when(userService.getUsers(any(PageRequest.class))).thenReturn(userListDto);

        // then
        mockMvc.perform(get("/api/user/list")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "name,asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.users").isArray())
                .andExpect(jsonPath("$.response.users.length()").value(2))
                .andExpect(jsonPath("$.response.users[0].name").value("User One"));
    }

    // ("/api/user/{id}")
    @Test
    public void 회원정보수정() throws Exception {
        // given
        Long id = 1L;

        UserUpdateResponseDto userUpdateResponseDto = UserUpdateResponseDto.builder()
                .nickname("newNickname")
                .name("New User")
                .phoneNumber("01087654321")
                .email("email@example.com")
                .build();

        // stub
        when(userService.updateUser(eq(id), any(UserUpdateRequestDto.class))).thenReturn(
                userUpdateResponseDto);

        // then
        mockMvc.perform(patch("/api/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                UserUpdateRequestDto.builder().build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(
                        jsonPath("$.response.nickname").value(userUpdateResponseDto.getNickname()))
                .andExpect(jsonPath("$.response.name").value(userUpdateResponseDto.getName()));
    }
}
