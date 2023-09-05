package com.rabobank.userservice.repositories;

import com.rabobank.userservice.entities.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Uzer, String> {

    Optional<Uzer> findByEmailIgnoreCase(String artist);

}
