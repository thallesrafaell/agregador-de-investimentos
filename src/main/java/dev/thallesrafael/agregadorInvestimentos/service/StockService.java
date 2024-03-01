package dev.thallesrafael.agregadorInvestimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateStockDto;
import dev.thallesrafael.agregadorInvestimentos.entity.Stock;
import dev.thallesrafael.agregadorInvestimentos.repository.StockRepository;

@Service
public class StockService {

  @Autowired
  private StockRepository stockRepository;

  public void createStock(CreateStockDto dto) {
    //DTO Entity
    var stock = new Stock(dto.stockId(), dto.description());

    stockRepository.save(stock);
  }
  
}
