package event_catalog_service.dtos.res;

import java.math.BigDecimal;

public record EventSectorDetailsDTO(
    String sectorId,
    String sectorName,
    BigDecimal sectorBasePrice,
    BigDecimal sectorHalfPrice,
    Boolean hasNumberedSeats
) {
}
