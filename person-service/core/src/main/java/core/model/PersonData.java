package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class PersonData {

    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private Date birthDt;
    private short age;
    private char sex;

    @MappedCollection(idColumn = "contact_id")
    private Set<Contact> contacts;

    @MappedCollection(idColumn = "medical_card_id")
    private MedicalCard medicalCard;

    @MappedCollection(idColumn = "parent_id")
    private Set<PersonData> parents;
}
