package com.example.kiosk.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PointErrorCode implements ErrorCode {

    INVALID_CHARGE_POINT(HttpStatus.BAD_REQUEST, "충전 금액은 0보다 커야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
