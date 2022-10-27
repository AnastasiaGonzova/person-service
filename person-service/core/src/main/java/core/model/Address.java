package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
public class Address {

    @Id
    private Long id;
    private Long countryId;
    private String city;
    private int postCode;
    private String street;
    private String building;
    private String flat;

    @MappedCollection(idColumn = "contact_id")
    private Set<Contact> contacts;
}
