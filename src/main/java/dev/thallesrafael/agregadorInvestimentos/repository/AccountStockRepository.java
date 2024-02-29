package dev.thallesrafael.agregadorInvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.thallesrafael.agregadorInvestimentos.entity.AccountStock;
import dev.thallesrafael.agregadorInvestimentos.entity.AccountStockId;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId>{
  
}
