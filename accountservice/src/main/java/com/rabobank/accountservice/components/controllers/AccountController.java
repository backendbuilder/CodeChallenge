package com.rabobank.accountservice.components.controllers;

import com.rabobank.accountservice.components.services.AccountService;
import com.rabobank.accountservice.domain.dtos.AccountDto;
import com.rabobank.accountservice.domain.dtos.TransferDto;
import com.rabobank.accountservice.domain.entities.Account;
import com.rabobank.accountservice.domain.exceptions.InsufficientFundsException;
import com.rabobank.accountservice.domain.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountController {

    public static final String NON_VALID_PARAM = "parameter value not valid";
    public static final String INSUFFICIENT_FUNDS = "Insufficient Funds";
    private AccountService accountService;

    @GetMapping("getById/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {
        try {
            return ResponseEntity.ok(accountService.getAccount(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getByEmail/{email}")
    public ResponseEntity<Object> getAccountsByEmail(@PathVariable String email){
        try {
            String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8.toString());
            return ResponseEntity.ok(accountService.getAccountsByEmail(decodedEmail));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.unprocessableEntity().body(NON_VALID_PARAM);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> putAccount(@RequestBody AccountDto accountDto) {
        try{
            AccountDto savedAccountDto = accountService.saveAccount(accountDto);
            return ResponseEntity.ok(savedAccountDto);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.unprocessableEntity().build();
        } catch (ConstraintViolationException e){
            return ResponseEntity.unprocessableEntity().body(NON_VALID_PARAM);
        }
    }

    @PutMapping("/transferMoney")
    public ResponseEntity<Object> transferMoney(@RequestBody TransferDto transferDto){
        try{
            AccountDto account = accountService.transferMoney(transferDto);
            return ResponseEntity.ok(account);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(INSUFFICIENT_FUNDS);
        } catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(NON_VALID_PARAM);
        }
    }

}
