package dto.external;

import org.springframework.data.annotation.Id;

public class RoleDto {

    @Id
    private Long id;
    private String roleName;
}
