package com.github.egosteva.tests;

import com.github.egosteva.models.lombok.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.egosteva.helpers.CustomAllureListener.withCustomTemplates;
import static com.github.egosteva.specs.CreateUserSpec.createUserRequestSpec;
import static com.github.egosteva.specs.CreateUserSpec.createUserResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReqresInApiModelsTests {


    @Test
    void createUserWithLombokCustomAllureStepsTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = step("Make request", () ->
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

        step("Check response name", () ->
                assertThat(createUserResponse.getName()).isEqualTo("morpheus"));
        step("Check response job", () ->
                assertThat(createUserResponse.getJob()).isEqualTo("leader"));
    }

    @Test
    void createUserWithLombokCustomAllureStepsSpescTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = step("Make user create request", () ->
                given(createUserRequestSpec)
                        .body(createUserBody)
                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseLombokModel.class));

        step("Check response name", () ->
                assertThat(createUserResponse.getName()).isEqualTo("morpheus"));
        step("Check response job", () ->
                assertThat(createUserResponse.getJob()).isEqualTo("leader"));
    }

    @Test
    void updateUserLombokTest() {
        //      String body = "{ \"job\": \"zion resident\" }";
        UpdateUserBodyLombokModel updateUserBody = new UpdateUserBodyLombokModel();
        updateUserBody.setJob("zion resident");

        UpdateUserResponseLombokModel updateUserResponse = step("Make user update request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().all()
                        .body(updateUserBody)
                        .contentType(JSON)
                        .when()
                        .patch("https://reqres.in/api/users/2")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(UpdateUserResponseLombokModel.class));
        //      .body("job", is("zion resident")));

        step("Check response job", () ->
                assertThat(updateUserResponse.getJob()).isEqualTo("zion resident"));
    }

    @Test
    void unsuccessfulRegisterLombokTest() {
        //    String body = "{ \"email\": \"sydney@fife\" }";
        UnsuccessfulRegisterBodyLombokModel unsuccessfulRegisterBody = new UnsuccessfulRegisterBodyLombokModel();
        unsuccessfulRegisterBody.setEmail("sydney@fife");

        UnsuccessfulRegisterResponseLombokModel unsuccessfulRegisterResponse = step("Make unsuccessful registration request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().all()
                        .body(unsuccessfulRegisterBody)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(400)
                        .extract().as(UnsuccessfulRegisterResponseLombokModel.class));
        //           .body("error", is("Missing password"));

        step("Check error text", () ->
                assertThat(unsuccessfulRegisterResponse.getError()).isEqualTo("Missing password"));
    }

    @Test
    void deleteUserSpecTest() {
        step("Make user delete request and check status code", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .when()
                        .delete("https://reqres.in/api/users/2")
                        .then()
                        .log().status()
                        .statusCode(204));
    }

    @Test
    void checkUsersListTest() {
        CheckUsersListResponseLombokModel checkUsersListResponse = step("Make get users list request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .when()
                        .get("https://reqres.in/api/users?page=2")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(CheckUsersListResponseLombokModel.class));

        List<DataResponseLombokModel> dataListResponse = checkUsersListResponse.getData();
        SupportResponseLombokModel supportResponse = checkUsersListResponse.getSupport();
        step("Check page number", () ->
                assertThat(checkUsersListResponse.getPage()).isEqualTo(2));
        step("Check id of the first user in the list", () ->
                assertThat(dataListResponse.get(0).id).isEqualTo(7));
        step("Check email of the first user in the list", () ->
                assertThat(dataListResponse.get(0).email).isEqualTo("michael.lawson@reqres.in"));
        step("Check first_name of the first user in the list", () ->
                assertThat(dataListResponse.get(0).first_name).isEqualTo("Michael"));
        step("Check last_name of the first user in the list", () ->
                assertThat(dataListResponse.get(0).last_name).isEqualTo("Lawson"));
        step("Check avatar of the first user in the list", () ->
                assertThat(dataListResponse.get(0).avatar).isEqualTo("https://reqres.in/img/faces/7-image.jpg"));
        step("Check support url", () ->
        assertThat(supportResponse.getUrl()).isEqualTo("https://reqres.in/#support-heading"));
    }
}