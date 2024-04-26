package com.thecommerce.app.domain.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("classpath:sql/UserDummyData.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserIdTest() {
        // given - UserDummyData.sql 스크립트를 통해 더미 데이터 삽입
        final String userId = "user01";

        // when
        Optional<User> foundUser = userRepository.findByUserId(userId);

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getUserId()).isEqualTo(userId);
    }

    @Test
    public void findByNicknameTest() {
        // given - UserDummyData.sql 스크립트를 통해 더미 데이터 삽입
        final String nickname = "nick01";

        // when
        Optional<User> foundUser = userRepository.findByNickname(nickname);

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getNickname()).isEqualTo(nickname);
    }
}
