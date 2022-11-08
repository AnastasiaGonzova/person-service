package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignalDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonDataDto personData;
}
