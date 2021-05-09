package adeo.leroymerlin.cdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import adeo.leroymerlin.cdp.model.EventEntity;

@Transactional
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    
}
