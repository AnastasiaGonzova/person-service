package core.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    private Long id;
    private String phoneNumber;
    private String email;
    private String profileLink;
}
