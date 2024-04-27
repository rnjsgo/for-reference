package com.thecommerce.app.domain.user.repository;

import com.thecommerce.app.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByNickname(String nickname);

    Page<User> findAll(Pageable pageable);

    // 다른 유저의 닉네임이 존재하는지 확인 (본인 닉네임 제외)
    Optional<User> findByNicknameAndIdNot(String nickname, Long id);
}
