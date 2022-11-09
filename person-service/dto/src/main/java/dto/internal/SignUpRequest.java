package dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private Date birthDt;
    private Short age;
    private Character sex;
    private String phoneNumber;
    private String email;
    private String profileLink;
}
