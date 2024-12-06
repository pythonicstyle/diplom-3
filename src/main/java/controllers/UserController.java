package controllers;

import static constants.Constants.*;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;

public class UserController {

    @Step("Создание пользователя")
    public Response createUser(String email, String pass, String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", pass);
        map.put("name", name);
        return Specification.getRequestSpecification()
            .body(map)
            .post(API_AUTH_REGISTER);
    }

    @Step("Получкение токена пользователя")
    public String getAuthToken(String email, String pass) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", pass);
        return Specification.getRequestSpecification()
            .body(map)
            .post(API_AUTH_LOGIN)
            .then()
            .extract()
            .body()
            .path("accessToken");
    }

    @Step("Удаление пользователя по токену")
    public Response deleteUser(String token) {
        return Specification.getRequestSpecification()
            .header("Authorization", token)
            .delete(API_AUTH_USER);
    }
}
