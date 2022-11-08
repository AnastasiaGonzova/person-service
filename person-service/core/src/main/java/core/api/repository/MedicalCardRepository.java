package core.api.repository;

import core.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
