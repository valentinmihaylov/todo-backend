package org.example.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.example.resource.dto.TodoDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class TodoResourceTest {

    @Test
    public void testGetAllTodos() {
        given()
                .get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreateNewTodo() {
        TodoDTO newTodo = TodoDTO.builder().description("unit-test").build();
        given()
                .contentType("application/json")
                .body(newTodo)
                .when()
                .post("/todo")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateAndRead() {
        TodoDTO newTodo = TodoDTO.builder().description("unit-test").build();
        given()
                .contentType("application/json")
                .body(newTodo)
                .when()
                .post("/todo")
                .then()
                .statusCode(201);

        given()
                .get("/todo")
                .then()
                .statusCode(200)
                .body("$", hasSize(1))
                .body("id", notNullValue());
    }

}
