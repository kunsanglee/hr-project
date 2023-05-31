package com.example.hrproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "History not founded"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurs"),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "Bad request error"),
    DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Department not founded"),
    ;

    private final HttpStatus status;
    private final String message;
}
