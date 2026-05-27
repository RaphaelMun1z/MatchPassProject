package event_catalog_service.repositories;

import event_catalog_service.dtos.res.EventDetailsResponseDTO;
import event_catalog_service.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {

    public Optional<EventDetailsResponseDTO> getEventDetails(String eventId);
}
