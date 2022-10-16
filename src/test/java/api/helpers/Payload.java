package api.helpers;

import io.qameta.allure.Step;

import java.util.HashMap;

public class Payload {

    @Step("Получить body для запроса получения гостевого токена")
    public static HashMap<String, String> getPayloadForGuestToken() {
        HashMap<String, String> payload = new HashMap<>();
        payload.put("grant_type", "client_credentials");
        payload.put("scope", "guest:default");
        return payload;
    }
}
