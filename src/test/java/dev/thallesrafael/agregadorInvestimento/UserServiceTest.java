package dev.thallesrafael.agregadorInvestimento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.thallesrafael.agregadorInvestimentos.controller.CreateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.controller.UpdateUserDTO;
import dev.thallesrafael.agregadorInvestimentos.entity.User;
import dev.thallesrafael.agregadorInvestimentos.repository.UserRepository;
import dev.thallesrafael.agregadorInvestimentos.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Captor
  private ArgumentCaptor<User> userCaptor;

  @Captor ArgumentCaptor<UUID> uuidCaptor;

  @Nested
  class createUser{
    @Test
    @DisplayName("Should create a user with success")
    void shouldCreateUserWithSuccess(){

      //Arrange
      var user = new User(UUID.randomUUID(),"username", "email@email.com", "123456789", Instant.now(),null);

      doReturn(user).when(userRepository).save(userCaptor.capture());
      var input = new CreateUserDTO("usrename", "email@email.com", "123456789");

      //Act
      userService.createUser(input);
      var userArgumentCaptor = userCaptor.getValue();

      //Assert
      assertEquals(input.username(), userArgumentCaptor.getUsername());
      assertEquals(input.email(), userArgumentCaptor.getEmail());
      assertEquals(input.password(), userArgumentCaptor.getPassword());
   }

   @Test
   @DisplayName("Should throw exception when Error occurs")
   void shouldThrowExceptionWhenErrorOccurs(){

     //Arrange
     doThrow (new RuntimeException()).when(userRepository).save(any());
     var input = new CreateUserDTO("usrename", "email@email.com", "123456789");



     //Assert
      assertThrows(RuntimeException.class, () -> userService.createUser(input));


    }
  }

  @Nested
  class getUserById{

    @Test
    @DisplayName("Should return a user with success")
    void shouldReturnUserWithSuccess(){

      //Arrange
      var user = new User(UUID.randomUUID(),"username", "email", "123456789", Instant.now(),null);
      doReturn(Optional.of(user)).when(userRepository).findById(uuidCaptor.capture());

      //Act
      var output = userService.getUserById(user.getId().toString());

      //Assert
      assertTrue(output.isPresent());
      assertEquals(user.getId(), output.get().getId());
    }

    @Test
    @DisplayName("Should return null when user not found")
    void shouldReturnNullWhenUserNotFound(){

      //Arrange
      var id = UUID.randomUUID();
      doReturn(Optional.empty()).when(userRepository).findById(uuidCaptor.capture());

      //Act
      var output = userService.getUserById(id.toString());

      //Assert
      assertTrue(output.isEmpty());
      assertEquals(id, uuidCaptor.getValue());

    }
  }

  @Nested
  class findAllUsers{

    @Test
    @DisplayName("Should return a list of users with success")
    void shouldReturnListOfUsersWithSuccess(){

      //Arrange
      var user1 = new User(UUID.randomUUID(),"username", "email", "123456789", Instant.now(),null);
      var user2 = new User(UUID.randomUUID(),"username", "email", "123456789", Instant.now(),null);
      var user3 = new User(UUID.randomUUID(),"username", "email", "123456789", Instant.now(),null);
      doReturn(List.of(user1, user2, user3)).when(userRepository).findAll();

      //Act
      var output = userService.getAllUsers();

      //Assert
      assertNotNull(output);
      assertEquals(3, output.size());
    }
  }


  @Nested
 class deleteUserById {
  @Test
  @DisplayName("Should delete user with success when user exists")
  void shouldDeleteUserWithSuccessWhenUserExists() {
    // Arrange
    var userId = UUID.randomUUID();

    // Captura o UUID para existsById e deleteById
    doNothing().when(userRepository).deleteById(userId);

    // Act
    userService.deleteUser(userId.toString());

    // Assert
    verify(userRepository, times(1)).deleteById(userId);

 }

 @Nested
  class updateUserById {

        @Test
        @DisplayName("Should update user by id when user exists and username and password is filled")
        void shouldUpdateUserByIdWhenUserExistsAndUsernameAndPasswordIsFilled() {

            // Arrange
            var updateUserDto = new UpdateUserDTO(
                "newUsername",
                "newPassword"
            );
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(uuidCaptor.capture());
            doReturn(user)
                    .when(userRepository)
                    .save(userCaptor.capture());

            // Act
            userService.updateUser(user.getId().toString(), updateUserDto);

            // Assert
            assertEquals(user.getId(), uuidCaptor.getValue());

            var userCaptured = userCaptor.getValue();

            assertEquals(updateUserDto.username(), userCaptured.getUsername());
            assertEquals(updateUserDto.password(), userCaptured.getPassword());

            verify(userRepository, times(1))
                    .findById(uuidCaptor.getValue());
            verify(userRepository, times(1)).save(user);
        }

        @Test
        @DisplayName("Should not update user when user not exists")
        void shouldNotUpdateUserWhenUserNotExists() {

            // Arrange
            var updateUserDto = new UpdateUserDTO(
                    "newUsername",
                    "newPassword"
            );
            var userId = UUID.randomUUID();
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(uuidCaptor.capture());

            // Act
            userService.updateUser(userId.toString(), updateUserDto);

            // Assert
            assertEquals(userId, uuidCaptor.getValue());

            verify(userRepository, times(1))
                    .findById(uuidCaptor.getValue());
            verify(userRepository, times(0)).save(any());
        }
    }

}
}

