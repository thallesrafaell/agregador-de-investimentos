package dev.thallesrafael.agregadorInvestimentos.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountResponseDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateAccountDto;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.UpdateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

  @Autowired
  private StockService stockService;
  

  @PostMapping
 public ResponseEntity<User> createUser(@RequestBody CreateUserDTO user){
     stockService.createStock(user);
   return ResponseEntity.ok().build();
 }

  
}
