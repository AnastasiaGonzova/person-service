package core.api.repository;

import core.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
