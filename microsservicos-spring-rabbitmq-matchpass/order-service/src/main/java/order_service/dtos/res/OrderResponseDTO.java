package order_service.dtos.res;

import order_service.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
    String orderId,
    BigDecimal totalAmount,
    OrderStatus status,
    String paymentUrl,
    List<OrderItemResponseDTO> itens
) {
}
