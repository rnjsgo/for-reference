package com.thecommerce.app.domain.user.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListDto {

    private List<UserDto> users;
    private PaginationInfo pagination;
}
