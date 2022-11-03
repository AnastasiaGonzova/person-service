package core.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Illness {

    @Id
    private Long id;
    private Long typeId;
    private Character heaviness;
    private Timestamp appearanceDttm;
    private Date recoveryDt;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "medical_card_id")
    private Set<MedicalCard> medicalCards;

    public void assignMedicalCard(MedicalCard medicalCard){
        this.medicalCards.add(medicalCard);
    }

}
