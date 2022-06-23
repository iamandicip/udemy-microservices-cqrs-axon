package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.core.models.BankAccount;
import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.queries.*;
import com.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var account = accountRepository.findById(query.getId());
        return account.map(
                        bankAccount -> new AccountLookupResponse("Bank account found!", bankAccount))
                .orElseGet(() -> new AccountLookupResponse("Bank account not found for id " + query.getId()));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        var account = accountRepository.findByAccountHolder(query.getAccountHolderId());
        return account.map(
                        bankAccount -> new AccountLookupResponse("Bank account found!", bankAccount)
                )
                .orElseGet(() -> new AccountLookupResponse("Bank account not found for account holder id "
                        + query.getAccountHolderId()));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountsIterator = accountRepository.findAll();
        if (!bankAccountsIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No bank accounts found!");
        }
        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountsIterator.forEach(bankAccounts::add);
        return new AccountLookupResponse("Bank accounts found " + bankAccounts.size(), bankAccounts);
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountWithBalance(FindAccountWithBalanceQuery query) {
        var bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccounts != null && bankAccounts.size() > 0
                ? new AccountLookupResponse("Bank accounts found " + bankAccounts.size(), bankAccounts)
                : new AccountLookupResponse("No bank accounts found");
    }
}
