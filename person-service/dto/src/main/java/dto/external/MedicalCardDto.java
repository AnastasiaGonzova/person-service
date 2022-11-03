package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardDto {

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
}
