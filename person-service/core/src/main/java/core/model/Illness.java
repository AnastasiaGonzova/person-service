package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class Illness {

    @Id
    private Long id;
    private Long typeId;
    private char heaviness;
    private Timestamp appearanceDttm;
    private Date recoveryDt;

    @MappedCollection(idColumn = "medical_card_id")
    private Set<MedicalCard> medicalCards;

}
