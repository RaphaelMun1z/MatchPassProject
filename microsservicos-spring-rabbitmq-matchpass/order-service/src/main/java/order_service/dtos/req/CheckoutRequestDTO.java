package order_service.dtos.req;

import java.util.List;

public record CheckoutRequestDTO(
    String userId,
    String eventId,
    List<OrderItemRequestDTO> items
) {
}
