package api.requests;

import api.helpers.Specifications;
import api.models.requests.PayloadForAuthorization;
import api.models.requests.PayloadForRegistrationNewPlayer;
import api.models.responses.AuthorizationResponse;
import api.models.responses.AuthorizationResponseWithAllData;
import api.models.responses.PlayerProfileDataResponse;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static api.data.AuthorizationData.BASIC_AUTH_LOGIN;
import static api.data.AuthorizationData.REGISTRATION_NEW_PLAYER_PASSWORD;
import static api.data.EndPointsData.PLAYERS;
import static api.data.EndPointsData.TOKEN;
import static api.helpers.DataGenerator.generateEnglishLetters;
import static api.helpers.Specifications.requestWithBasicAuth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Requests {

    private static final String URL = "http://test-api.d6.dev.devcaz.com";

    @Step("Получить токен гостя")
    public static final String getGuestToken() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Map<String, String> payload = new HashMap<>();
        payload.put("grant_type", "client_credentials");
        payload.put("scope", "guest:default");
        String token = requestWithBasicAuth(BASIC_AUTH_LOGIN, "")
            .body(payload)
            .when()
            .post(TOKEN)
            .then()
            .body("token_type", equalTo("Bearer"))
            .extract().body().jsonPath().getString("access_token");
        Assert.assertTrue(token.length() > 0);
        return token;
    }

    @Step("Регистрация нового игрока")
    public static final PlayerProfileDataResponse getRegistrationNewPlayerResponse() {
        String token = getGuestToken();
        PayloadForRegistrationNewPlayer payload = new PayloadForRegistrationNewPlayer(
            generateEnglishLetters(10), REGISTRATION_NEW_PLAYER_PASSWORD,
            REGISTRATION_NEW_PLAYER_PASSWORD + "=", generateEnglishLetters(10) + "@gmail.com");

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        String response =  given()
            .header("Authorization", "Bearer " + token)
            .body(payload)
            .when()
            .post(PLAYERS)
            .then()
            .extract().asPrettyString();
        return new Gson().fromJson(response, PlayerProfileDataResponse.class);
    }

    @Step("Успешная авторизация")
    public static final AuthorizationResponseWithAllData getSuccessfulAuthorizationResponseWithAllData() {
        PlayerProfileDataResponse registrationResponse = getRegistrationNewPlayerResponse();
        PayloadForAuthorization payload = new PayloadForAuthorization(
            "password", registrationResponse.getUsername(), REGISTRATION_NEW_PLAYER_PASSWORD);

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        String authorizationResponseString = requestWithBasicAuth(BASIC_AUTH_LOGIN, "")
            .body(payload)
            .when()
            .post(TOKEN)
            .then()
            .extract().asPrettyString();
        return new AuthorizationResponseWithAllData(registrationResponse,
            new Gson().fromJson(authorizationResponseString, AuthorizationResponse.class));
    }
}
