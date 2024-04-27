package com.thecommerce.app.domain.user.dto.response;

import com.thecommerce.app.domain.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
public class UserListDto {

    private List<UserDto> users;
    private PaginationInfo pagination;

    public static UserListDto of(final Page<User> users) {
        return UserListDto.builder()
                .users(users.getContent().stream().map(UserDto::fromEntity)
                        .collect(Collectors.toList()))
                .pagination(PaginationInfo.of(users))
                .build();
    }
}
