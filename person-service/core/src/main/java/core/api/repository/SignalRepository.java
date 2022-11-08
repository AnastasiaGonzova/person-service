package core.api.repository;

import core.model.Signal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignalRepository extends JpaRepository<Signal, Long> {
}
