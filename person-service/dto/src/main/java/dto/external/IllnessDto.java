package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IllnessDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long typeId;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Character heaviness;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp appearanceDttm;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date recoveryDt;

    @ManyToOne
    @JoinColumn(name="medical_card_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MedicalCardDto medicalCard;
}
