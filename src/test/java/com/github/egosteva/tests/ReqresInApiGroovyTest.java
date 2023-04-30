package com.github.egosteva.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.github.egosteva.specs.Specifications.checkUsersListResponseSpec;
import static com.github.egosteva.specs.Specifications.requestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@DisplayName("Reqres.in API tests")
@Tag("api")
public class ReqresInApiGroovyTest {

    @Test
    @DisplayName("Check Users List using Groovy")
    void checkUsersListGroovyTest() {
                step("Make get users list request and check user data", () ->
                given(requestSpec)
                        .get("/users?page=2")
                        .then()
                        .spec(checkUsersListResponseSpec)
                        .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                                hasItem("michael.lawson@reqres.in"))
                        .body("data.findAll{it.first_name =~/./}.first_name.flatten()",
                                hasItem("Michael"))
                        .body("data.last_name[0]", equalTo("Lawson")));
    }
}