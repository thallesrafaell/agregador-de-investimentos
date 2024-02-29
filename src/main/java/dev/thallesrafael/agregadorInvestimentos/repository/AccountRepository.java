package dev.thallesrafael.agregadorInvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.thallesrafael.agregadorInvestimentos.entity.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{
  
}
