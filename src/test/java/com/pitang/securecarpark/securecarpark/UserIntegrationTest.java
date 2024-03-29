package com.pitang.securecarpark.securecarpark;

import com.pitang.securecarpark.securecarpark.controller.dto.car.CarRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.exception.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIntegrationTest {

    @Autowired
    WebTestClient testClient;

    @Test
    public void TestSucessCreateUserValid () {
        UserResponse responseBody = testClient.post().uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserRequest("Teste", "Criacao", "teste5@gmail.com", new Date(124, 7, 23), "tester", "pssw0rds", "988888888", new ArrayList<CarRequest>()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getEmail().equals("teste5@gmail.com"));
        Assertions.assertThat(responseBody.getFirstName().equals("Teste"));
        Assertions.assertThat(responseBody.getLastName().equals("Criacao"));
    }

    @Test
    public void testFailedCreateUserInvalid () {
        ErrorResponse responseBody = testClient.post().uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserRequest("Teste", "Criacao", "", new Date(124, 7, 23), "tester", "pssw0rds", "988888888", new ArrayList<CarRequest>()))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
    }

    @Test
    public void testFailedCreateUserValidDuplicate () {
        ErrorResponse responseBody = testClient.post().uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserRequest("Teste", "Criacao", "teste@gmail.com", new Date(124, 7, 23), "tester", "pssw0rds", "988888888", new ArrayList<CarRequest>()))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
    }

    //Falta de tempo de construir todos os testes de integração

}
