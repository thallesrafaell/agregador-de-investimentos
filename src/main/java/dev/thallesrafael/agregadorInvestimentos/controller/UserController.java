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

import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateAccountDto;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.CreateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.dto.UpdateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;
  

  @PostMapping
 public ResponseEntity<User> createUser(@RequestBody CreateUserDTO user){
    var userId = userService.createUser(user);
   return ResponseEntity.created(URI.create("/users/" + userId)).build() ;
 }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable String id){
    var user = userService.getUserById(id);
    if(user.isPresent()){
      return ResponseEntity.ok(user.get());
    }
    return ResponseEntity.notFound().build();
    
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    var users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UpdateUserDTO user){
    userService.updateUser(id, user);
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id){
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
  @PostMapping("/{id}/account")
  public ResponseEntity<Void> deleteUser(@PathVariable String id, @RequestBody CreateAccountDto createAccountDto){
    userService.createAccount(id, createAccountDto);
    return ResponseEntity.noContent().build();
  }
}
