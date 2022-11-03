package core.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.sql.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonData {

    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private Date birthDt;
    private Short age;
    private Character sex;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "contact_id")
    private Set<Contact> contacts;

    @MappedCollection(idColumn = "medical_card_id")
    private MedicalCard medicalCard;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "parent_id")
    private Set<PersonData> parents;

    public void assignContact(Contact contact){
        this.contacts.add(contact);
    }
    public void assignMedicalCard(MedicalCard medicalCard){
        this.medicalCard = medicalCard;
    }
    public void assignParent(PersonData parent){
        if(this.id != parent.getId()){
            this.parents.add(parent);
        }
        else{
            throw new IllegalArgumentException("It's one person!");
        }
    }
}
