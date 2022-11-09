package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long typeId;
    private Character heaviness;
    private Timestamp appearanceDttm;
    private Date recoveryDt;

    @ManyToOne
    @JoinColumn(name="medical_card_id")
    private MedicalCard medicalCard;

}
