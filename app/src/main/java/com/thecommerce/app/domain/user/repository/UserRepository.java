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
}
