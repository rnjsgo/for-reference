package com.thecommerce.app.domain.user.exception;

public class AlreadyExistNicknameException extends RuntimeException {

    @Override
    public String getMessage() {
        return UserExceptionMessage.NICKNAME_ALREADY_EXIST.getMessage();
    }
}