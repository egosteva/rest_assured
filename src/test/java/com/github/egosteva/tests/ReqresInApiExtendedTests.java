package com.github.egosteva.tests;

import com.github.egosteva.tests.models.pojo.CreateUserBodyPojoModel;
import com.github.egosteva.tests.models.pojo.CreateUserResponsePojoModel;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInApiExtendedTests {


    @Test
    void createUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body(matchesJsonSchemaInClasspath("schemas/createUserResponseSchema.json"));

        ////////////////////////
    }

    @Test
    void createUserWithPojoTest() {
        //      String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        CreateUserBodyPojoModel createUserBody = new CreateUserBodyPojoModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponsePojoModel createUserResponse = given()
                .log().all()
                .body(createUserBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponsePojoModel.class);
        //     .body("name", is("morpheus"))
        //    .body("job", is("leader"))
        //        .body(matchesJsonSchemaInClasspath("schemas/createUserResponseSchema.json"));
        assertThat(createUserResponse.getName()).isEqualTo("morpheus");
        assertThat(createUserResponse.getJob()).isEqualTo("leader");
    }

    @Test
    void createUserWithLombokTest() {


        CreateUserBodyPojoModel createUserBody = new CreateUserBodyPojoModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponsePojoModel createUserResponse = given()
                .log().all()
                .body(createUserBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponsePojoModel.class);
        //     .body("name", is("morpheus"))
        //    .body("job", is("leader"))
        //        .body(matchesJsonSchemaInClasspath("schemas/createUserResponseSchema.json"));
        assertThat(createUserResponse.getName()).isEqualTo("morpheus");
        assertThat(createUserResponse.getJob()).isEqualTo("leader");
    }
}