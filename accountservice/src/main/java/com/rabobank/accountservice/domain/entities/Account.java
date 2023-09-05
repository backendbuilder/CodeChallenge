package com.rabobank.accountservice.domain.entities;

import com.rabobank.accountservice.domain.dtos.AccountDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    private String email;
    @Size(min = 2, max = 50)
    private String name;
    @PositiveOrZero
    private BigDecimal balance;

    public static Account fromDto(AccountDto accountDto){
        Account account =  new Account();
        account.setEmail(accountDto.email());
        account.setName(accountDto.name());
        account.setBalance(accountDto.balance());
        return account;
    }

}
