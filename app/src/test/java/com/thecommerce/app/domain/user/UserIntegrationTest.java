package com.thecommerce.app.domain.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import com.thecommerce.app.domain.user.dto.request.UserUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();


    // ("api/user/join")
    @Test
    public void 회원가입() throws Exception {

        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .userId("newUser")
                .password("password")
                .nickname("newNickname")
                .name("New User")
                .phoneNumber("01012345678")
                .email("user01@example.com")
                .build();

        mockMvc.perform(post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(userSignUpDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ("api/user/list")
    @Test
    @Sql("classpath:sql/UserDummyData.sql")
    public void 회원목록조회() throws Exception {

        mockMvc.perform(get("/api/user/list")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "name,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.users").isArray())
                .andExpect(jsonPath("$.response.users[0].name").isNotEmpty())
                .andDo(print());
    }

    // ("/api/user/{id}")
    @Test
    @Sql("classpath:sql/UserDummyData.sql")
    public void 회원정보수정() throws Exception {

        Long id = 1L;
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .nickname("newNickname")
                .name("New User")
                .phoneNumber("01087654321")
                .email("newEmail@example.com")
                .build();

        mockMvc.perform(patch("/api/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userUpdateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.response.nickname").value(userUpdateRequestDto.getNickname()))
                .andExpect(jsonPath("$.response.name").value(userUpdateRequestDto.getName()))
                .andExpect(jsonPath("$.response.phoneNumber").value(
                        userUpdateRequestDto.getPhoneNumber()))
                .andExpect(jsonPath("$.response.email").value(userUpdateRequestDto.getEmail()))
                .andDo(print());
    }
}