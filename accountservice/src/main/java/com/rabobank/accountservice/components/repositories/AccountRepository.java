package com.rabobank.accountservice.components.repositories;

import com.rabobank.accountservice.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByEmailIgnoreCase(String artist);

}
