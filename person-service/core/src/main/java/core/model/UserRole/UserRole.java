package core.model.UserRole;

import core.model.Role;
import core.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.persistence.EmbeddedId;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleKey id;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "user_id")
    private Set<User> users;

    @Setter(PRIVATE)
    @MappedCollection(idColumn = "role_id")
    private Set<Role> roles;
}
