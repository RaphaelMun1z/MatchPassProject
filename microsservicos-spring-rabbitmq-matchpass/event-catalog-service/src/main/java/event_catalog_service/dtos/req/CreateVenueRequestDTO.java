package event_catalog_service.dtos.req;

import event_catalog_service.entities.Sector;

import java.util.List;

public record CreateVenueRequestDTO(
    String name,
    String city,
    String state,
    Integer totalCapacity,
    List<SectorRequestDTO> sectors
) {
}
