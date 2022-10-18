package api.models.responses;

import lombok.Data;

@Data
public class PlayerProfileDataResponse {

    private Integer id;
    private Integer country_id;
    private Integer timezone_id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String phone_number;
    private String birthdate;
    private Boolean bonuses_allowed;
    private Boolean is_verified;

    public PlayerProfileDataResponse(Integer id, Integer country_id,
                                     Integer timezone_id, String username,
                                     String email, String name, String surname,
                                     String gender, String phone_number,
                                     String birthdate, Boolean bonuses_allowed,
                                     Boolean is_verified) {
        this.id = id;
        this.country_id = country_id;
        this.timezone_id = timezone_id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone_number = phone_number;
        this.birthdate = birthdate;
        this.bonuses_allowed = bonuses_allowed;
        this.is_verified = is_verified;
    }
}
