package dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardInternalDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Character clientStatus;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Character medStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date registryDt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String commentAbout;

    @OneToOne(mappedBy = "medicalCard")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonDataInternalDto personData;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "medicalCard")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<IllnessInternalDto> illnesses;
}
