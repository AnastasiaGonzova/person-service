package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="person_data")
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private Date birthDt;
    private Short age;
    private Character sex;

    @OneToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    @OneToOne
    @JoinColumn(name="medical_card_id")
    private MedicalCard medicalCard;

    @OneToOne(mappedBy = "personData")
    private User user;

    @Setter(PRIVATE)
    @OneToMany
    @JoinColumn(name="parent_id")
    private Set<PersonData> parents;

    @Setter(PRIVATE)
    @OneToMany
    @JoinColumn(name="signal_id")
    private Set<Signal> signals;

    public void assignParent(PersonData parent){
        this.parents.add(parent);
    }

    public void removeParent(PersonData parent){
        this.parents.remove(parent);
    }
}
