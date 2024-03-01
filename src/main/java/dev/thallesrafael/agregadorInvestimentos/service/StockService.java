package dev.thallesrafael.agregadorInvestimentos.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.thallesrafael.agregadorInvestimentos.controller.dto.AccountResponseDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateAccountDto;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.UpdateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.entity.Account;
import dev.thallesrafael.agregadorInvestimentos.entity.BillingAddress;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.repository.AccountRepository;
import dev.thallesrafael.agregadorInvestimentos.repository.BillingAddressRepository;
import dev.thallesrafael.agregadorInvestimentos.repository.UserRepository;

@Service
public class StockService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private BillingAddressRepository billingAddressRepository;


  public UUID createUser(CreateUserDTO user){
    User newUser = new User(UUID.randomUUID(),
      user.username(), 
      user.email(),
      user.password(), 
      Instant.now(), 
      null);
      var userSaved = userRepository.save(newUser);
    return userSaved.getId();
  }

  public Optional<User> getUserById(String string){
    return userRepository.findById(UUID.fromString(string));
  } 

  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

  public void deleteUser(String id){
    userRepository.deleteById(UUID.fromString(id));
  }

  public Void updateUser(String id, UpdateUserDTO user) {
    var userId = UUID.fromString(id);
    var userEntity = userRepository.findById(userId);
    if(userEntity.isPresent()){
      var userToUpdate = userEntity.get();
      if (user.username() != null) {
        userToUpdate.setUsername(user.username());
      }
      if (user.password() != null) {
        userToUpdate.setPassword(user.password());
      }

      userRepository.save(userToUpdate);
    }
    return null;
  }

  public void createAccount(String id, CreateAccountDto createAccountDto) {
    User user = userRepository.findById(UUID.fromString(id))
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); 
    //DTO to Entity
    var account = new Account(
      UUID.randomUUID(),
      createAccountDto.description(),
      user,
      null,
      new ArrayList<>()
    );

    var accoutnCreated = accountRepository.save(account);

    var billingAddress = new BillingAddress(
      accoutnCreated.getId(),
      createAccountDto.street(),
      createAccountDto.number(),
      account
    );

    billingAddressRepository.save(billingAddress);

  }

  public List<AccountResponseDTO> listAccount(String id) {
    User user = userRepository.findById(UUID.fromString(id))
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); 

    return user.getAccounts().stream().map(ac -> new AccountResponseDTO(ac.getId().toString(), ac.getDescription()))
    .toList();
  }
  
}
