package com.springbank.user.query.api.dto;

import com.springbank.user.core.models.User;
import dto.BaseResponse;


import java.util.Collections;
import java.util.List;


public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(String message) {
        super(message);
    }

    public UserLookupResponse(String message, User user) {
        super(message);
        this.users = Collections.singletonList(user);
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(User user) {
        super(null);
        this.users = Collections.singletonList(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
