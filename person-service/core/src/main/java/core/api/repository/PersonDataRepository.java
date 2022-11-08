package core.api.repository;

import core.model.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {
}
