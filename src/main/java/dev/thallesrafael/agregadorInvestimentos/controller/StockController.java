package dev.thallesrafael.agregadorInvestimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateStockDto;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

  @Autowired
  private StockService stockService;
  

  @PostMapping
 public ResponseEntity<User> createUser(@RequestBody CreateStockDto dto){
     stockService.createStock(dto);
   return ResponseEntity.ok().build();
 }

  
}
