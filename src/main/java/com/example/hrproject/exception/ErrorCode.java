package com.example.hrproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "History not founded")
    ;

    private final HttpStatus status;
    private final String message;
}
