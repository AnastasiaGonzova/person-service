package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class Address {

    @Id
    private Long id;
    private Long countryId;
    private String city;
    private Integer postCode;
    private String street;
    private String building;
    private String flat;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

}
