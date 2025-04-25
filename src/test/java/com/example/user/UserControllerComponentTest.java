package com.example.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)  //  Test only the controller
public class UserControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;  //  Mock service

    @Autowired
    private ObjectMapper objectMapper;

    /*
      Component Test:
      Tests POST /users endpoint, verifies status and response body.
      Service is mocked. DB is NOT involved.
     */
    @Test
    void shouldCreateUser() throws Exception {
        // Arrange
        User inputUser = new User();
        inputUser.setName("Linda");
        inputUser.setEmail("linda@example.com");

        User mockResponseUser = new User();
        mockResponseUser.setId(1L);
        mockResponseUser.setName("Linda");
        mockResponseUser.setEmail("linda@example.com");

        when(userService.createUser(any(User.class))).thenReturn(mockResponseUser);

        // Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Linda"))
                .andExpect(jsonPath("$.email").value("linda@example.com"));
    }
}