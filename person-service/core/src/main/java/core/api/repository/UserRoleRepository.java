package core.api.repository;

import core.model.UserRole.UserRole;
import core.model.UserRole.UserRoleKey;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleKey> {
}
