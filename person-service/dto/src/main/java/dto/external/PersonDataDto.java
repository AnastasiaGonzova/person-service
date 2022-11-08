package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDataDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birthDt;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Short age;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Character sex;

    @OneToOne
    @JoinColumn(name="contact_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContactDto contact;

    @OneToOne
    @JoinColumn(name="medical_card_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MedicalCardDto medicalCard;

    @OneToMany
    @JoinColumn(name="parent_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<PersonDataDto> parents;
}
