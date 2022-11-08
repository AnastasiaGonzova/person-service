package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    private Long id;
    private String phoneNumber;
    private String email;
    private String profileLink;

    @OneToOne(mappedBy = "contact")
    private Address address;

    @OneToOne(mappedBy = "contact")
    private PersonData personData;

}
