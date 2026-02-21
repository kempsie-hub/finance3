package com.my.expense.util;

import com.my.expense.dto.ApiResponseDTO;

import static com.my.expense.constant.Constants.APPLICATION_NAME;
import static com.my.expense.constant.Constants.SUCCESS;

/**
 * Small utility to build ApiResponseDTO objects.
 */
public class ResponseUtil {

    // application name placed on all responses; keep it simple and constant for now


    /**
     * Build an ApiResponseDTO with status, message and optional data.
     *
     * @param status  status string (e.g. "success" / "error")
     * @param message human-readable message
     * @param data    response payload (may be null)
     * @param <T>     payload type
     * @return ApiResponseDTO populated with provided values
     */
    public static <T> ApiResponseDTO<T> of(String status, String message, T data) {
        return new ApiResponseDTO<>(APPLICATION_NAME, status, message, data);
    }

    public static <T> ApiResponseDTO<T> success( String message, T data) {
        return new ApiResponseDTO<>(APPLICATION_NAME,  SUCCESS, message, data);
    }

    /**
     * Convenience overload when there's no data to return.
     */
    public static <T> ApiResponseDTO<T> of(String status, String message) {
        return of(status, message, null);
    }

}
