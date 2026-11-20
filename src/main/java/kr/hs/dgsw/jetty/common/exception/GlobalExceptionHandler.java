package kr.hs.dgsw.jetty.common.exception;


import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleApplicationException(ApplicationException exception) {
        var statusCode = exception.getStatusCode();
        ErrorResponse error = ErrorResponse.of(
                statusCode.getStatus(),
                statusCode.getMessage()
        );
        return ResponseEntity
                .status(statusCode.getStatus())
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> details = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> details.putIfAbsent(error.getField(), error.getDefaultMessage()));

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.BAD_REQUEST.getStatus(),
                CommonStatusCode.BAD_REQUEST.getMessage(),
                details
        );

        return ResponseEntity
                .status(CommonStatusCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ApiResponse<ErrorResponse>> handleInvalidRequest(Exception exception) {
        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.BAD_REQUEST.getStatus(),
                CommonStatusCode.BAD_REQUEST.getMessage()
        );

        return ResponseEntity
                .status(CommonStatusCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INTERNAL_SERVER_ERROR.getStatus(),
                CommonStatusCode.INTERNAL_SERVER_ERROR.getMessage()
        );
        exception.printStackTrace();
        return ResponseEntity
                .status(error.status())
                .body(ApiResponse.error(error));
    }
}
