package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="medical_card")
public class MedicalCard {

    @Id
    private Long id;
    private String clientStatus;
    private String medStatus;
    private Date registryDt;
    private String commentAbout;

    @OneToOne(mappedBy = "medicalCard")
    private PersonData personData;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "medicalCard")
    private Set<Illness> illnesses;
}
