package com.rabobank.accountservice.components.services;

import com.rabobank.accountservice.components.repositories.AccountRepository;

import com.rabobank.accountservice.domain.dtos.AccountDto;
import com.rabobank.accountservice.domain.dtos.TransferDto;
import com.rabobank.accountservice.domain.entities.Account;
import com.rabobank.accountservice.domain.exceptions.InsufficientFundsException;
import com.rabobank.accountservice.domain.exceptions.NotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    /**
     * Saves an Account in the database.
     * @param accountDto holds all the data to create a Account entity
     * @return the saved Account converted to a AccountDto
     */
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = accountRepository.save(Account.fromDto(accountDto));
        return AccountDto.fromEntity(account);
    }

    /**
     * Retrieves an Account from the database based on its id.
     * @param id of the Account
     * @return a AccountDto that holds all the neccessary info of the Account entity
     * @throws NotFoundException if the id does not exist in the database
     */
    public AccountDto getAccount(long id) throws NotFoundException {
        return AccountDto.fromEntity(accountRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    /**
     * Transfers money from the balance of 1 account to another
     * @param transferDto holding, the sending account, receiving account and amount to be transfered.
     * @return the transferDto
     * @throws NotFoundException if one of the id's does not exist in the database
     * @throws InsufficientFundsException if the balance on the sending account is too low
     */

    public AccountDto transferMoney(TransferDto transferDto) throws Exception {
        Account fromAccount = accountRepository.findById(transferDto.from()).orElseThrow(NotFoundException::new);
        Account toAccount = accountRepository.findById(transferDto.to()).orElseThrow(NotFoundException::new);

        if (fromAccount.getBalance().doubleValue() < transferDto.amount().doubleValue()){
            throw new InsufficientFundsException();
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferDto.amount()));
        toAccount.setBalance(toAccount.getBalance().add(transferDto.amount()));
        accountRepository.saveAll(List.of(fromAccount, toAccount));

        return AccountDto.fromEntity(fromAccount);

    }

    /**
     * Retrieves all Accounts belonging to an email.
     * @param email belonging to the Account
     * @return a AccountDto that holds all the neccessary info of the Account entity

     */
    public List<AccountDto> getAccountsByEmail(String email) {
        return accountRepository.findAllByEmailIgnoreCase(email)
                .stream()
                .map(AccountDto::fromEntity)
                .toList();

    }


}
