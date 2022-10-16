package api.models.responses;

import lombok.Data;

@Data
public class AuthorizationResponseWithAllData {
    private PlayerProfileDataResponse playerProfileDataResponse;
    private AuthorizationResponse authorizationResponse;

    public AuthorizationResponseWithAllData(PlayerProfileDataResponse playerProfileDataResponse,
                                            AuthorizationResponse authorizationResponse) {
        this.playerProfileDataResponse = playerProfileDataResponse;
        this.authorizationResponse = authorizationResponse;
    }
}
