package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="signal")
public class Signal {

    @Id
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    private PersonData personData;
}
