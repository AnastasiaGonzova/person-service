package core.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private Long id;
    private Long countryId;
    private String city;
    private Integer postCode;
    private String street;
    private String building;
    private String flat;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "contact_id")
    private Set<Contact> contacts;

    public void assignContact(Contact contact){
        this.contacts.add(contact);
    }
}
