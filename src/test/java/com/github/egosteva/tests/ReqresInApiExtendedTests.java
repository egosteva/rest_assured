package com.github.egosteva.tests;

import com.github.egosteva.tests.models.lombok.CreateUserBodyLombokModel;
import com.github.egosteva.tests.models.lombok.CreateUserResponseLombokModel;
import com.github.egosteva.tests.models.pojo.CreateUserBodyPojoModel;
import com.github.egosteva.tests.models.pojo.CreateUserResponsePojoModel;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

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
    }

    @Test
    void createUserWithPojoTest() {
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

        assertThat(createUserResponse.getName()).isEqualTo("morpheus");
        assertThat(createUserResponse.getJob()).isEqualTo("leader");
    }

    @Test
    void createUserWithLombokTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = given()
                .log().all()
                .body(createUserBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseLombokModel.class);

        assertThat(createUserResponse.getName()).isEqualTo("morpheus");
        assertThat(createUserResponse.getJob()).isEqualTo("leader");
    }
}