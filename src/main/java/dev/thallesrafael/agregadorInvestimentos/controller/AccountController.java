package dev.thallesrafael.agregadorInvestimentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountStockAssociateDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountStockResponseDto;
import dev.thallesrafael.agregadorInvestimentos.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  

  @Autowired
  private AccountService accountService;


  @PostMapping("/{id}/stocks")
  public ResponseEntity<Void> createAccount(@PathVariable String id, @RequestBody AccountStockAssociateDTO dto ){
    accountService.associateStock(id, dto);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/{id}/stocks")
  public ResponseEntity<List<AccountStockResponseDto>> createAccount(@PathVariable String id){
    var stocks =accountService.listStock(id);
    return ResponseEntity.ok(stocks);
  }
}
