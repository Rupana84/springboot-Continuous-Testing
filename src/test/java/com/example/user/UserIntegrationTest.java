    package com.example.user;

    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.test.context.ActiveProfiles;
    import org.springframework.test.context.TestPropertySource;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;


    @ActiveProfiles("test")
    @SpringBootTest
    @Transactional
    public class UserIntegrationTest {

        @Autowired
        private UserService userService;

        @Autowired
        private UserRepository userRepository;

        //  Mock external dependency to keep the test isolated from other services
        @MockBean
        private ProductClient productClient;

        //Integration Test---> Test 1: Save and fetch user from test DB

        @Test
        void shouldCreateAndFetchUserFromDatabase() {
            // Arrange
            User user = new User();
            user.setName("Singh");
            user.setEmail("singh@example.com");

            // Act
            User savedUser = userService.createUser(user);
            Optional<User> fetchedUser = userService.getUserById(savedUser.getId());

            // Assert
            assertTrue(fetchedUser.isPresent());
            assertEquals("singh@example.com", fetchedUser.get().getEmail());
        }


         // Test 2: Update user in DB and verify changes

        @Test
        void shouldUpdateUserInDatabase() {
            // Arrange
            User user = new User();
            user.setName("Jag");
            user.setEmail("jag@example.com");
            User saved = userService.createUser(user);

            // Act
            User updateData = new User();
            updateData.setName("Tobias");
            updateData.setEmail("tobias@example.com");

            userService.updateUser(saved.getId(), updateData);
            Optional<User> updated = userService.getUserById(saved.getId());

            // Assert
            assertTrue(updated.isPresent());
            assertEquals("Tobias", updated.get().getName());
        }


         //Test 3: Delete user and ensure removal

        @Test
        void shouldDeleteUserFromDatabase() {
            // Arrange
            User user = new User();
            user.setName("DeleteMe");
            user.setEmail("deleteme@example.com");
            User saved = userService.createUser(user);

            // Act
            boolean deleted = userService.deleteUser(saved.getId());
            Optional<User> found = userService.getUserById(saved.getId());

            // Assert
            assertTrue(deleted);
            assertFalse(found.isPresent());
        }
    }