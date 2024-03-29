package com.pitang.securecarpark.securecarpark;

import com.pitang.securecarpark.securecarpark.controller.dto.car.CarRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.car.CarResponse;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.entity.Car;
import com.pitang.securecarpark.securecarpark.exception.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/sql/cars/cars-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "/sql/cars/cars-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarIntegrationTest {

    @Autowired
    WebTestClient testClient;

//    @Test
//    public void TestSucessCreateCarValid () {
//        CarResponse responseBody = testClient.post().uri("/api/v1/cars")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(new CarRequest(2020, "PDV-0524", "audi","white"))
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(CarResponse.class)
//                .returnResult().getResponseBody();
//
//        Assertions.assertThat(responseBody).isNotNull();
//        Assertions.assertThat(responseBody).isNotNull();
//        Assertions.assertThat(responseBody.getId()).isNotNull();
//        Assertions.assertThat(responseBody.getColor().equals("white"));
//        Assertions.assertThat(responseBody.getYear()).isEqualTo(2020);
//        Assertions.assertThat(responseBody.getModel().equals("audi"));
//        Assertions.assertThat(responseBody.getLicensePlate().equals("PDV-0524"));
//    }
//
//    @Test
//    public void testFailedCreateCarInvalid () {
//        ErrorResponse responseBody = testClient.post().uri("/api/v1/cars")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(new CarRequest(2020, "PDV-05224", "","white"))
//                .exchange()
//                .expectStatus().isEqualTo(422)
//                .expectBody(ErrorResponse.class)
//                .returnResult().getResponseBody();
//
//        Assertions.assertThat(responseBody).isNotNull();
//    }
//
//    @Test
//    public void testFailedCreateUserValidDuplicate () {
//        ErrorResponse responseBody = testClient.post().uri("/api/v1/cars")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(new CarRequest(2020, "PDV-0626", "audi","white"))
//                .exchange()
//                .expectStatus().isEqualTo(409)
//                .expectBody(ErrorResponse.class)
//                .returnResult().getResponseBody();
//
//        Assertions.assertThat(responseBody).isNotNull();
//    }

    //Falta de tempo de construir todos os testes de integração e liberar autenticação para testes
}
