package matheus.andrew.brick.app.model;

import lombok.Data;

@Data
public class UserData {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String confirmPassword;
}
