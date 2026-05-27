package event_catalog_service.dtos.res;

import java.math.BigDecimal;
import java.util.UUID;

public record SectorResponseDTO(
    String sectorId,
    String sectorName,
    Boolean hasNumberedSeats
) {
}
