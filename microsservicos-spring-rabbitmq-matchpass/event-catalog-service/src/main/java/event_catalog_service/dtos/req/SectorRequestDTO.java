package event_catalog_service.dtos.req;

import java.math.BigDecimal;

public record SectorRequestDTO(
    String name,
    Integer capacity,
    BigDecimal basePrice,
    BigDecimal halfPrice,
    Boolean hasNumberedSeats
) {
}
