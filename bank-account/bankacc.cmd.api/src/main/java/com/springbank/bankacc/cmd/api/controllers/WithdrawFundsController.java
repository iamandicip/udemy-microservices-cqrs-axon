package com.springbank.bankacc.cmd.api.controllers;

import com.springbank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.springbank.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.springbank.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/withdrawFunds")
@Validated
public class WithdrawFundsController {
    private final CommandGateway commandGateway;

    @Autowired
    public WithdrawFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);

            // send() returns a CompletableFuture, is async
            // the get() makes it sync so if there is an error thrown in handling the event, it propagates here
            commandGateway.send(command).get();

            return new ResponseEntity<>(new BaseResponse("Funds successfully withdrawn!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to withdraw funds from bank account for id: " + id;
            System.out.println(e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.BAD_REQUEST);
        }
    }
}
