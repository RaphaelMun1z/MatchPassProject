package event_catalog_service.dtos.res;

import java.util.List;
import java.util.UUID;

public record CreatedVenueResponseDTO(
    UUID id,
    String name,
    String city,
    String state,
    Integer totalCapacity,
    List<SectorPricingResponseDTO> sectors
) {
}
