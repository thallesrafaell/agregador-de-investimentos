package dev.thallesrafael.agregadorInvestimentos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountResponseDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountStockAssociateDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountStockResponseDto;
import dev.thallesrafael.agregadorInvestimentos.entity.AccountStock;
import dev.thallesrafael.agregadorInvestimentos.entity.AccountStockId;
import dev.thallesrafael.agregadorInvestimentos.repository.AccountRepository;
import dev.thallesrafael.agregadorInvestimentos.repository.AccountStockRepository;
import dev.thallesrafael.agregadorInvestimentos.repository.StockRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private AccountStockRepository accountStockRepository;

  public void associateStock(String id, AccountStockAssociateDTO dto) {
    var account = accountRepository.findById(UUID.fromString(id))
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    var stock = stockRepository.findById(dto.stockId())
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    //DTO to Entity
    var idStock = new AccountStockId(account.getId(), stock.getStockId());
    var entity = new AccountStock(idStock,account,stock, dto.quantity());

    accountStockRepository.save(entity);
  }

    public List<AccountStockResponseDto> listStock(String id) {
      var account = accountRepository.findById(UUID.fromString(id))
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
      return account.getAccountStocks()
      .stream()
      .map(as -> new AccountStockResponseDto(as.getStock().getStockId(), as.getQuantity(), 0.0)).toList();
    }
}
