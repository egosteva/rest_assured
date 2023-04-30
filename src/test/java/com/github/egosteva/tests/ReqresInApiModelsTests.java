package com.github.egosteva.tests;

import com.github.egosteva.models.lombok.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.egosteva.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReqresInApiModelsTests {

    @Test
    void createUserModelsTest() {
        CreateUserBodyLombokModel createUserBody = new CreateUserBodyLombokModel();
        createUserBody.setName("morpheus");
        createUserBody.setJob("leader");

        CreateUserResponseLombokModel createUserResponse = step("Make user create request", () ->
                given(requestSpec)
                        .body(createUserBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseLombokModel.class));

        step("Check response name", () ->
                assertThat(createUserResponse.getName()).isEqualTo("morpheus"));
        step("Check response job", () ->
                assertThat(createUserResponse.getJob()).isEqualTo("leader"));
    }

    @Test
    void updateUserModelsTest() {
        UpdateUserBodyLombokModel updateUserBody = new UpdateUserBodyLombokModel();
        updateUserBody.setJob("zion resident");

        UpdateUserResponseLombokModel updateUserResponse = step("Make user update request", () ->
                given(requestSpec)
                        .body(updateUserBody)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UpdateUserResponseLombokModel.class));

        step("Check response job", () ->
                assertThat(updateUserResponse.getJob()).isEqualTo("zion resident"));
    }

    @Test
    void unsuccessfulRegisterModelsTest() {
        UnsuccessfulRegisterBodyLombokModel unsuccessfulRegisterBody = new UnsuccessfulRegisterBodyLombokModel();
        unsuccessfulRegisterBody.setEmail("sydney@fife");

        UnsuccessfulRegisterResponseLombokModel unsuccessfulRegisterResponse = step("Make unsuccessful registration request", () ->
                given(requestSpec)
                        .body(unsuccessfulRegisterBody)
                        .when()
                        .post("/register")
                        .then()
                        .spec(unsuccessfulRegisterResponseSpec)
                        .extract().as(UnsuccessfulRegisterResponseLombokModel.class));

        step("Check error text", () ->
                assertThat(unsuccessfulRegisterResponse.getError()).isEqualTo("Missing password"));
    }

    @Test
    void deleteUserModelsTest() {
        step("Make user delete request and check status code", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteUserResponseSpec));
    }

    @Test
    void checkUsersListModelsTest() {
        CheckUsersListResponseLombokModel checkUsersListResponse = step("Make get users list request", () ->
                given(requestSpec)
                        .get("/users?page=2")
                        .then()
                        .spec(checkUsersListResponseSpec)
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