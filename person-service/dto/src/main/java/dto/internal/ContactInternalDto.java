package dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInternalDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileLink;

    @OneToOne(mappedBy = "contact")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressInternalDto address;

    @OneToOne(mappedBy = "contact")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonDataInternalDto personData;
}
