package com.example.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * REST Controller for managing `User` resources.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /**
     * Creates a new User.
     *
     * @param user The details of the user to create. Must be valid.
     * @return The created user in the response.
     */
    @PostMapping
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);

    }

    /**
     * Fetches all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to fetch. Must not be null.
     * @return The user if they exist, or a 404 Not Found status if they don't exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @NotNull Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates the details of an existing user.
     *
     * @param id The ID of the user to update. Must not be null.
     * @param updatedUser The updated details of the user. Must be valid.
     * @return The updated user details or a 404 Not Found status if the user doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @NotNull Long id, @Valid @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete. Must not be null.
     * @return A status of 204 No Content if successful, or 404 Not Found if the user doesn't exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NotNull Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves products from the external Product Service.
     *
     * @return The products as a string.
     */
    @GetMapping("/products-from-service")
    public ResponseEntity<String> getProductsFromProductService() {
        String products = userService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}