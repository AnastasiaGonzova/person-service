package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("contact")
public class Contact {

    @Id
    private Long id;
    private String phoneNumber;
    private String email;
    private String profileLink;
}
