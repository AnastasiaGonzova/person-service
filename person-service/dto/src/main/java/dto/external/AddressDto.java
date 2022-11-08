package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long countryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String city;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer postCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String street;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String building;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String flat;

    @OneToOne
    @JoinColumn(name = "contact_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContactDto contact;
}
