package core.api.repository;

import core.model.Illness;
import org.springframework.data.repository.CrudRepository;

public interface IllnessRepository extends CrudRepository<Illness, Long> {
}
