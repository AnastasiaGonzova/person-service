package core.api.repository;

import core.model.Illness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IllnessRepository extends JpaRepository<Illness, Long> {
}
