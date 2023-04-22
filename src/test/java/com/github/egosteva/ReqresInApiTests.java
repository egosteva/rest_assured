package com.github.egosteva;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class ReqresInApiTests {

    @Test
    void CheckUsersListTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("data[0].id", is(7))
                .body("data[0].first_name", is("Michael"))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body(matchesJsonSchemaInClasspath("schemas/usersListResponseSchema.json"));
    }

    @Test
    void CreateUserTest() {
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
    void UpdateUserTest() {
        String body = "{ \"job\": \"zion resident\" }";
        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    void UnsuccessfulRegisterTest() {
        String body = "{ \"email\": \"sydney@fife\" }";
        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void DeleteUserTest() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}


