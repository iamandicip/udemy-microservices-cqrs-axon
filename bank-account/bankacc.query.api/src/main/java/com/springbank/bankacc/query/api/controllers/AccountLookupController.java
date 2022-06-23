package com.springbank.bankacc.query.api.controllers;

import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.queries.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/bankAccountLookup")
public class AccountLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {

            var query = new FindAllAccountsQuery();
            var response =
                    queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete all accounts request";
            System.out.println(safeErrorMessage);

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {

            var query = new FindAccountByIdQuery(id);
            var response =
                    queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by id request";
            System.out.println(safeErrorMessage);

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolderId/{holderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "holderId") String holderId) {
        try {

            var query = new FindAccountByHolderIdQuery(holderId);
            var response =
                    queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by holder id request";
            System.out.println(safeErrorMessage);

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(
            @PathVariable(value = "equalityType") EqualityType equalityType,
            @PathVariable(value = "balance") double balance) {
        try {

            var query = new FindAccountWithBalanceQuery(equalityType, balance);
            var response =
                    queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by equality type with balance request";
            System.out.println(safeErrorMessage);

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
