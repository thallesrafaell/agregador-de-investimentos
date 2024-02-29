package dev.thallesrafael.agregadorInvestimentos.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_accounts_stocks")
public class AccountStock {
  

  @EmbeddedId
  private AccountStockId id;

  @ManyToOne
  @MapsId("accountId")
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @MapsId("stockId")
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @Column(name = "quantity")
  private Integer quantity;

  private AccountStock() {
  }

  public AccountStock(AccountStockId id ,Account account, Stock stock, Integer quantity) {
    this.account = account;
    this.stock = stock;
    this.quantity = quantity;
    this.id = id;
  }

  public AccountStockId getId() {
    return id;
  }

  public void setId(AccountStockId id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AccountStock other = (AccountStock) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
}
