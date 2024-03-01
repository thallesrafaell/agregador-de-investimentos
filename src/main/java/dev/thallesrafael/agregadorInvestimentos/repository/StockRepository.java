package dev.thallesrafael.agregadorInvestimentos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import dev.thallesrafael.agregadorInvestimentos.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{
  
}
