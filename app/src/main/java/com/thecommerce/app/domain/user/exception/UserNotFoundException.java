package com.thecommerce.app.domain.user.exception;

public class UserNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        return UserExceptionMessage.USER_NOT_FOUND.getMessage();
    }

}
