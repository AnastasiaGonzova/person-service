package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

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

    @MappedCollection(idColumn = "contact_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<ContactDto> contacts;

    @MappedCollection(idColumn = "medical_card_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MedicalCardDto medicalCard;

    @MappedCollection(idColumn = "parent_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<PersonDataDto> parents;
}
