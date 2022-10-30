package core.api.repository;

import core.model.MedicalCard;
import org.springframework.data.repository.CrudRepository;

public interface MedicalCardRepository extends CrudRepository<MedicalCard, Long> {
}
