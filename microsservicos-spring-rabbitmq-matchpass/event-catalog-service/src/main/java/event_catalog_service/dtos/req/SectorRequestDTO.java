package event_catalog_service.dtos.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record SectorRequestDTO(
    @NotNull String name,
    @NotNull @PositiveOrZero Integer capacity,
    @NotNull Boolean hasNumberedSeats
) {
}
