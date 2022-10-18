package api.models.requests;

import lombok.Data;

@Data
public class PayloadForRegistrationNewPlayer {

    private String username;
    private String password_change;
    private String password_repeat;
    private String email;

    public PayloadForRegistrationNewPlayer(String username, String password_change, String password_repeat, String email) {
        this.username = username;
        this.password_change = password_change;
        this.password_repeat = password_repeat;
        this.email = email;
    }
}
