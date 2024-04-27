package com.thecommerce.app.domain.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.thecommerce.app.domain.user.entity.User;
import com.thecommerce.app.domain.user.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("classpath:sql/UserDummyData.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 유저아이디로_회원조회() {
        // given - UserDummyData.sql 스크립트를 통해 더미 데이터 삽입
        final String userId = "user01";

        // when
        Optional<User> foundUser = userRepository.findByUserId(userId);

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getUserId()).isEqualTo(userId);
    }

    @Test
    public void 닉네임으로_회원조회() {
        // given - UserDummyData.sql 스크립트를 통해 더미 데이터 삽입
        final String nickname = "nick01";

        // when
        Optional<User> foundUser = userRepository.findByNickname(nickname);

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getNickname()).isEqualTo(nickname);
    }

    @Test
    public void 회원목록조회_이름순() {
        Pageable sortedByName = PageRequest.of(0, 10, Sort.by("name").ascending());
        List<User> usersByName = userRepository.findAll(sortedByName).getContent();

        usersByName.forEach(
                p -> System.out.println(p.getId() + " " + p.getName())
        );

        assertThat(usersByName).isSortedAccordingTo(Comparator.comparing(User::getName));
    }

    @Test
    public void 회원목록조회_가입일순() {
        Pageable sortedByCreatedAt = PageRequest.of(0, 10, Sort.by("createdAt").ascending());
        List<User> usersByCreatedAt = userRepository.findAll(sortedByCreatedAt).getContent();

        usersByCreatedAt.forEach(
                p -> System.out.println(p.getId() + " " + p.getCreatedAt())
        );

        assertThat(usersByCreatedAt).isSortedAccordingTo(Comparator.comparing(User::getCreatedAt));
    }

}
