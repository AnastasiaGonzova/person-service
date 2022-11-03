package core.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCard {

    @Id
    private Long id;
    private Character clientStatus;
    private Character medStatus;
    private Date registryDt;
    private String commentAbout;
}
