package api.models.requests;

import lombok.Data;

@Data
public class PayloadForAuthorization {

    private String grant_type;
    private String username;
    private String password;

    public PayloadForAuthorization(String grant_type, String username, String password) {
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;
    }
}
