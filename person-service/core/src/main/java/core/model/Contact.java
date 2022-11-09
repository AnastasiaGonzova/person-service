package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String email;
    private String profileLink;

    @OneToOne(mappedBy = "contact")
    private Address address;

    @OneToOne(mappedBy = "contact")
    private PersonData personData;

}
