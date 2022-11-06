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
    private String clientStatus;
    private String medStatus;
    private Date registryDt;
    private String commentAbout;
}
