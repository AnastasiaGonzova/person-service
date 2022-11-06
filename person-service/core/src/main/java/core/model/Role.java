package core.model;

import core.model.UserRole.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    private String roleName;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "role_id")
    private Set<UserRole> users;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
