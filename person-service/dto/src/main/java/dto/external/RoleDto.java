package dto.external;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;

public class RoleDto {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;
}
