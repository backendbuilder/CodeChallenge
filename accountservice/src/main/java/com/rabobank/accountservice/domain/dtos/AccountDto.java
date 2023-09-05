package com.rabobank.accountservice.domain.dtos;

import com.rabobank.accountservice.domain.entities.Account;

import java.math.BigDecimal;

public record AccountDto(long id, String email, String name, BigDecimal balance){

    public static AccountDto fromEntity(Account account) {
       return new AccountDto(account.getId(), account.getEmail(), account.getName(), account.getBalance());
    }
}
