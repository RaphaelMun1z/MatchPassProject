package event_catalog_service.dtos.res;

import java.util.List;
import java.util.UUID;

public record VenueResponseDTO(
    String id,
    String name,
    String city,
    String state,
    Integer totalCapacity,
    List<SectorResponseDTO> sectors
) {
}
