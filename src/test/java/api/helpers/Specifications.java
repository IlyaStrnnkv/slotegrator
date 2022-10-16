package api.helpers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    /**
     * Specification for requests
     * @param url - base url
     * @return request
     */
    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
            .setBaseUri(url)
            .setContentType(ContentType.JSON)
            .build();
    }

    /**
     * Specification for response
     * @param statusCode - expected status code
     * @return response
     */
    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
            .expectStatusCode(statusCode)
            .build();
    }

    /**
     * Installing Specification
     * @param request - request specification
     * @param response - response specification
     */
    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    /**
     * Request specification
     * @param login auth login
     * @param pass auth pass
     * @return request
     */
    @Step("Передача в запрос логина и пароля Basic auth")
    public static RequestSpecification requestWithBasicAuth(String login, String pass) {
        return RestAssured
            .given()
            .auth()
            .preemptive()
            .basic(login, pass)
            .contentType(ContentType.JSON);
    }
}
