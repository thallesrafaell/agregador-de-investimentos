package dev.thallesrafael.agregadorInvestimentos.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddress {
  
  @Id
  @Column(name = "account_id")
  private UUID id;

  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private Integer number;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @MapsId
  @JoinColumn(name = "account_id")
  private Account account;

  public BillingAddress() {
  }

 

  public BillingAddress(UUID id, String street, Integer number, Account account) {
    this.id = id;
    this.street = street;
    this.number = number;
    this.account = account;
  }



  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Account getAccount() {
    return account;
  }



  public void setAccount(Account account) {
    this.account = account;
  }
  

}
