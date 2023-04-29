package com.github.egosteva.tests;

import com.github.egosteva.models.lombok.CreateUserBodyLombokModel;
import com.github.egosteva.models.lombok.CreateUserResponseLombokModel;
import com.github.egosteva.models.pojo.CreateUserBodyPojoModel;
import com.github.egosteva.models.pojo.CreateUserResponsePojoModel;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static com.github.egosteva.helpers.CustomAllureListener.withCustomTemplates;
import static com.github.egosteva.specs.CreateUserSpec.createUserRequestSpec;
import static com.github.egosteva.specs.CreateUserSpec.createUserResponseSpec;
import static io.qameta.allure.Allure.step;
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

    @Test
    void createUserWithLombokAllureTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = given()
                .filter(new AllureRestAssured())
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

    @Test
    void createUserWithLombokCustomAllureTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = given()
                .filter(withCustomTemplates())
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

    @Test
    void createUserWithLombokCustomAllureStepsTest() {
        step("Start test");
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = step("Make request", ()->
                given()
                .filter(withCustomTemplates())
                .log().all()
                .body(createUserBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseLombokModel.class));

        step("Check response name", ()->
        assertThat(createUserResponse.getName()).isEqualTo("morpheus"));
        step("Check response job", ()->
        assertThat(createUserResponse.getJob()).isEqualTo("leader"));
    }

    @Test
    void createUserWithLombokCustomAllureStepsSpescTest() {
        step("Start test");
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = step("Make request", ()->
                given(createUserRequestSpec)
                        .body(createUserBody)
                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseLombokModel.class));

        step("Check response name", ()->
                assertThat(createUserResponse.getName()).isEqualTo("morpheus"));
        step("Check response job", ()->
                assertThat(createUserResponse.getJob()).isEqualTo("leader"));
    }
}