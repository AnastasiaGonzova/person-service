package dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInternalDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
