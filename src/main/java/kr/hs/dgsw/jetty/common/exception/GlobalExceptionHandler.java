package kr.hs.dgsw.jetty.common.exception;


import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INTERNAL_SERVER_ERROR.getStatus(),
                CommonStatusCode.INTERNAL_SERVER_ERROR.getMessage()
        );

        return ResponseEntity
                .status(error.status())
                .body(ApiResponse.error(error));
    }
}
