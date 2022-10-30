package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Getter
@Setter
public class MedicalCard {

    @Id
    private Long id;
    private Character clientStatus;
    private Character medStatus;
    private Date registryDt;
    private String commentAbout;
}
