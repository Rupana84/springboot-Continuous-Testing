# Spring Boot Testing Examination Project

## 📋 Overview
This project demonstrates the implementation of unit, component and integration testing
in a Spring Boot application using JUnit 5, Mockito, and MockMvc.

It is part of the final examination for the "Testing in Spring Boot" course.

---

## 🎯 Goals

 Demonstrate 3 test levels:  
- Unit tests  
- Component tests (controller + service)  
- Integration tests (full flow with MySQL)  

 Use appropriate annotations:
- `@Mock`, `@InjectMocks` for unit tests  
- `@MockBean`, `@SpringBootTest`, `@AutoConfigureMockMvc` for component tests  
- `@Transactional`, real DB setup for integration tests  

 Proper test isolation  
 Clear structure and naming  
 Use environment variables for test DB

---

##  Testing Strategy

| Type | Description |
-----------------------
| Unit Test | Isolated test of `UserService` methods using `@Mock` and `@InjectMocks`. |
| Component Test | Tests `UserController` with real `UserService`, mocks `UserRepository`. Uses `MockMvc` for endpoint testing. |
| Integration Test | Full stack: Controller → Service → Repository → MySQL DB. Uses test profile and `@Transactional` for rollback. |

By Gurprreet singh Rupana

