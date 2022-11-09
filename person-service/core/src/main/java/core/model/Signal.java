package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="signal")
public class Signal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    private PersonData personData;

    public void assignPersonData(PersonData personData){
        this.personData = personData;
        personData.getSignals().add(this);
    }

    public void removePersonData(PersonData personData){
        personData.getSignals().remove(this);
        this.personData = null;
    }
}
