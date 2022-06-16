package com.springbank.user.cmd.api.dto;

import com.springbank.user.core.models.User;
import lombok.Data;

public class RegisterUserResponse extends BaseResponse{

    public String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
