package api.tests;

import api.helpers.Specifications;
import api.models.requests.PayloadForAuthorization;
import api.models.requests.PayloadForRegistrationNewPlayer;
import api.models.responses.AuthorizationResponse;
import api.models.responses.AuthorizationResponseWithAllData;
import api.models.responses.PlayerProfileDataResponse;
import com.google.gson.Gson;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static api.data.AuthorizationData.BASIC_AUTH_LOGIN;
import static api.data.AuthorizationData.REGISTRATION_NEW_PLAYER_PASSWORD;
import static api.data.EndPointsData.PLAYERS;
import static api.data.EndPointsData.TOKEN;
import static api.helpers.DataGenerator.generateEnglishLetters;
import static api.helpers.Payload.getPayloadForGuestToken;
import static api.helpers.Specifications.requestWithBasicAuth;
import static api.requests.Requests.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Devcaz")
public class ApiTest {

    private static final String URL = "http://test-api.d6.dev.devcaz.com";

    @Feature("Получение токена гостя")
    @Story("Получить токен гостя")
    @Test(description = "Получить токен гостя")
    @Severity(SeverityLevel.BLOCKER)
    public final void checkGettingGuestToken() {
        Map<String, String> payload = getPayloadForGuestToken();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        String token = requestWithBasicAuth(BASIC_AUTH_LOGIN, "")
            .body(payload)
            .when()
            .post(TOKEN)
            .then()
            .body("token_type", equalTo("Bearer"))
            .extract().body().jsonPath().getString("access_token");
        Assert.assertTrue(token.length() > 0);
    }

    @Feature("Регистрация")
    @Story("Успешная регистрация нового игрока")
    @Test(description = "Успешная регистрация нового игрока")
    @Severity(SeverityLevel.BLOCKER)
    public final void checkRegistrationNewPlayer() {
        String token = getGuestToken();
        PayloadForRegistrationNewPlayer payload = new PayloadForRegistrationNewPlayer(
            generateEnglishLetters(10), REGISTRATION_NEW_PLAYER_PASSWORD,
            REGISTRATION_NEW_PLAYER_PASSWORD + "=", generateEnglishLetters(10) + "@gmail.com");

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Response response = given()
            .header("Authorization", "Bearer " + token)
            .body(payload)
            .when()
            .post(PLAYERS)
            .then()
            .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("username"), payload.getUsername());
        Assert.assertEquals(jsonPath.getString("email"), payload.getEmail());
        Assert.assertTrue(jsonPath.getString("id").length() > 0);
    }

    @Feature("Авторизация")
    @Story("Авторизация под созданным игроком")
    @Test(description = "Авторизация под созданным игроком")
    @Severity(SeverityLevel.BLOCKER)
    public final void checkSuccessAuthorization() {
        PlayerProfileDataResponse response = getRegistrationNewPlayerResponse();
        PayloadForAuthorization payload = new PayloadForAuthorization(
            "password", response.getUsername(), REGISTRATION_NEW_PLAYER_PASSWORD);

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        String authorizationResponseString = requestWithBasicAuth(BASIC_AUTH_LOGIN, "")
            .body(payload)
            .when()
            .post(TOKEN)
            .then()
            .extract().asPrettyString();
        AuthorizationResponse authorizationResponse = new Gson()
            .fromJson(authorizationResponseString, AuthorizationResponse.class);
        Assert.assertEquals(authorizationResponse.getToken_type(), "Bearer");
        Assert.assertFalse(authorizationResponse.getAccess_token().isEmpty());
        Assert.assertEquals(authorizationResponse.getExpires_in(), (Integer) 86400);
        Assert.assertFalse(authorizationResponse.getRefresh_token().isEmpty());
    }

    @Feature("Запрос данных профиля игрока")
    @Story("Запрос данных существующего профиля игрока")
    @Test(description = "Запрос данных существующего профиля игрока")
    @Severity(SeverityLevel.CRITICAL)
    public final void checkSuccessfulGettingPlayerProfile() {
        AuthorizationResponseWithAllData authorization = getSuccessfulAuthorizationResponseWithAllData();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        String stringResponse = given()
            .header("Authorization", "Bearer " + authorization.getAuthorizationResponse().getAccess_token())
            .when()
            .get(PLAYERS + "/" + authorization.getPlayerProfileDataResponse().getId())
            .then()
            .extract().asString();
        PlayerProfileDataResponse response = new Gson().fromJson(stringResponse, PlayerProfileDataResponse.class);
        Assert.assertEquals(authorization.getPlayerProfileDataResponse().getId(), response.getId());
        Assert.assertEquals(authorization.getPlayerProfileDataResponse().getUsername(), response.getUsername());
        Assert.assertEquals(authorization.getPlayerProfileDataResponse().getEmail(), response.getEmail());
    }

    @Feature("Запрос данных профиля игрока")
    @Story("Запрос данных профиля другого игрока")
    @Test(description = "Запрос данных профиля другого игрока")
    @Severity(SeverityLevel.NORMAL)
    public final void checkUnSuccessfulGettingPlayerProfile() {
        AuthorizationResponseWithAllData authorization = getSuccessfulAuthorizationResponseWithAllData();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(404));
        Integer idForRequest = authorization.getPlayerProfileDataResponse().getId() - 1;
        String message = given()
            .header("Authorization", "Bearer " + authorization.getAuthorizationResponse().getAccess_token())
            .when()
            .get(PLAYERS + "/" + idForRequest)
            .then()
            .extract().response().jsonPath().getString("message");
        Assert.assertEquals(message, "Object not found: " + idForRequest);
    }
}
