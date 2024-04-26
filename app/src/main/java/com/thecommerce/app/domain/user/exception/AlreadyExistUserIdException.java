package com.thecommerce.app.domain.user.exception;

public class AlreadyExistUserIdException extends RuntimeException {

    @Override
    public String getMessage() {
        return UserExceptionMessage.USERID_ALREADY_EXIST.getMessage();
    }
}
