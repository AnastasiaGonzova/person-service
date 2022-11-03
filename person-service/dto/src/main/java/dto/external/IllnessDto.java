package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

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

    @MappedCollection(idColumn = "medical_card_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<MedicalCardDto> medicalCards;
}
