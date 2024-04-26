package com.thecommerce.app.domain.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecommerce.app.domain.user.dto.request.UserSignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
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

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSignUpDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }
}