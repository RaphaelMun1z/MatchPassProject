package inventory_service.repositories;

import inventory_service.entities.SeatLock;
import inventory_service.entities.enums.SeatStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SeatLockRepository extends CrudRepository<SeatLock, String> {
    List<SeatLock> findByEventIdAndSectorId(String eventId, String sectorId);

    Optional<SeatLock> findByEventIdAndSectorIdAndSeatTag(
        String eventId,
        String sectorId,
        String seatTag
    );

    List<SeatLock> findByEventIdAndStatus(String eventId, SeatStatusEnum status);

    List<SeatLock> findByUserId(String userId);
}
