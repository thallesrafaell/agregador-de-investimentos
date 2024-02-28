package dev.thallesrafael.agregadorInvestimentos.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thallesrafael.agregadorInvestimentos.controller.CreateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.UpdateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


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
  
}
