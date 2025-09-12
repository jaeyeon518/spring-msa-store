package com.example.userservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public CommonResponseDto() {
        this.success = true;
        this.message = "success";
    }

    public CommonResponseDto(T data) {
        this.success = true;
        this.message = "success";
        this.data = data;
    }
}
