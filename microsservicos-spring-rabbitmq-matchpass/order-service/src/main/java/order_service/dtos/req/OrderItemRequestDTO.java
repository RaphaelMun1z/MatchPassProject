package order_service.dtos.req;

import order_service.entities.enums.TicketType;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
    String sectorId,
    String seatTag,
    TicketType ticketType,
    BigDecimal appliedPrice
) {
}
