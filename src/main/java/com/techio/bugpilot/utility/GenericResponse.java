package com.techio.bugpilot.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class GenericResponse<T> {

    @Setter
    private boolean success;

    @Setter
    private String message;

    private T data;

    @Setter
    private int totalRecords;

    public GenericResponse() {
    }

    public GenericResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.setData(data);
    }

    public void setData(T data) {
        this.data = data;

        if (data instanceof List<?>) {
            this.totalRecords = ((List<?>) data).size();
        } else if (data != null) {
            this.totalRecords = 1;
        } else {
            this.totalRecords = 0;
        }
    }
}
