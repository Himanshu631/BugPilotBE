package com.techio.bugpilot.utility;

public class GeneralUtility {

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(true, "Success", data);
    }

    public static <T> GenericResponse<T> success(String message, T data) {
        return new GenericResponse<>(true, message, data);
    }

    public static <T> GenericResponse<T> failure(String message) {
        return new GenericResponse<>(false, message, null);
    }

    public static <T> GenericResponse<T> failure(String message, T data) {
        return new GenericResponse<>(false, message, data);
    }
}

