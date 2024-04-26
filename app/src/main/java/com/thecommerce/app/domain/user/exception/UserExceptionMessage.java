package com.thecommerce.app.domain.user.exception;

public enum UserExceptionMessage {
    USERID_ALREADY_EXIST("이미 존재하는 유저 ID 입니다."),
    NICKNAME_ALREADY_EXIST("이미 존재하는 닉네임 입니다.");

    private final String message;

    UserExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
