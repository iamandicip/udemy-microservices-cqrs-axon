package com.springbank.user.cmd.api.dto;

import dto.BaseResponse;

public class RegisterUserResponse extends BaseResponse {

    public String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
