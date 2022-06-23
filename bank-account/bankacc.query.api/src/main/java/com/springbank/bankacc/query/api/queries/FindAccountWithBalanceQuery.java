package com.springbank.bankacc.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery {
    private EqualityType equalityType;
    private double balance;
}
