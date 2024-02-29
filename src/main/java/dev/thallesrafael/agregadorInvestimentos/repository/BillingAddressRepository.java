package dev.thallesrafael.agregadorInvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.thallesrafael.agregadorInvestimentos.entity.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID>{
  
}
