package com.springbank.bankacc.query.api.dto;

import com.springbank.bankacc.core.dto.BaseResponse;
import com.springbank.bankacc.core.models.BankAccount;

import java.util.Collections;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }

    public AccountLookupResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookupResponse(String message, BankAccount account) {
        super(message);
        this.accounts = Collections.singletonList(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
}
