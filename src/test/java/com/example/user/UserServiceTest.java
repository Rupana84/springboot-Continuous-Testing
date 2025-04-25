package com.example.user;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


     //Unit Test ---> createUser when email does not exist.

    @Test
    void createUser_shouldSaveUser_whenEmailNotExists() {
        // Arrange
        User user = new User();
        user.setName("rupana");
        user.setEmail("rupana@mail.com");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertEquals("rupana", result.getName());
        verify(userRepository).save(user);
    }


     //getUserById returns user.

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Bil");
        user.setEmail("bil@mail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Bil", result.get().getName());
        verify(userRepository).findById(1L);
    }

      //deleteUser with valid ID.

    @Test
    void deleteUser_shouldReturnTrue_whenUserExists() {
        // Arrange
        when(userRepository.existsById(2L)).thenReturn(true);

        // Act
        boolean deleted = userService.deleteUser(2L);

        // Assert
        assertTrue(deleted);
        verify(userRepository).deleteById(2L);
    }
}
