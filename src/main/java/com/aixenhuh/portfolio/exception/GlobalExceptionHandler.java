package com.aixenhuh.portfolio.exception;

import com.aixenhuh.portfolio.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
//ExceptionHandler 하나의 클래스가 아닌 모든 @Controller 예외 처리
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomAuthenticationException.class)
    protected ResponseEntity<CommonResponse> handleCustomAuthenticationException(CustomAuthenticationException e) {

        log.info("handleCustomAuthenticationException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<CommonResponse> handleLoginFailedException(LoginFailedException e) {

        log.info("handleLoginFailedException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomJwtRuntimeException.class)
    protected ResponseEntity<CommonResponse> handleJwtException(CustomJwtRuntimeException e) {

        log.info("handleJwtException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.INVALID_JWT_TOKEN.getCode())
                .message(ErrorCode.INVALID_JWT_TOKEN.getMessage())
                .status(ErrorCode.INVALID_JWT_TOKEN.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
