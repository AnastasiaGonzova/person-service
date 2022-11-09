package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void assignContact(Contact contact){
        this.contact = contact;
        contact.setAddress(this);
    }

    public void removeContact(Contact contact){
        contact.setAddress(null);
        this.contact = null;
    }

}
