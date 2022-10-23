package core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Getter
@Setter
@Table("medical_card")
public class MedicalCard {

    @Id
    private Long id;
    private char clientStatus;
    private char medStatus;
    private Date registryDt;
    private String comment;
}
