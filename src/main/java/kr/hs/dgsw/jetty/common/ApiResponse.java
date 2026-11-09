package kr.hs.dgsw.jetty.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        T data,
        ErrorResponse error
) {

    public ApiResponse(T data) {
        this(true, data, null);
    }
    public ApiResponse() {
        this(true, null, null);
    }

    public ApiResponse(ErrorResponse error) {
        this(false, null, error);
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return new ApiResponse<>(error);
    }
}
