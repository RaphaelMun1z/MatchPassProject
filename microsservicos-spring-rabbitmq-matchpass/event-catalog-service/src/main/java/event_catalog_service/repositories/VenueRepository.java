package event_catalog_service.repositories;

import event_catalog_service.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, String> {
    List<Venue> findByCityIgnoreCaseAndStateIgnoreCase(String city, String state);
}
