package order_service.services;

import order_service.dtos.req.CheckoutRequestDTO;
import order_service.dtos.req.OrderItemRequestDTO;
import order_service.dtos.res.OrderSummaryResponseDTO;
import order_service.entities.Order;
import order_service.entities.OrderItem;
import order_service.entities.enums.OrderStatus;
import order_service.repositories.OrderItemRepository;
import order_service.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderSummaryResponseDTO processCheckout(CheckoutRequestDTO request) {
        BigDecimal totalAmount = request.items()
            .stream()
            .map(OrderItemRequestDTO::appliedPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order newOrder = new Order(
            request.eventId(),
            request.userId(),
            totalAmount,
            OrderStatus.PENDING
        );

        List<OrderItem> newOrderItems = request.items()
            .stream().map(dto -> new OrderItem(
                dto.sectorId(),
                dto.seatTag(),
                dto.ticketType(),
                dto.appliedPrice()
            )).toList();

        newOrder.addItems(newOrderItems);

        Order savedOrder = orderRepository.save(newOrder);

        return new OrderSummaryResponseDTO(
            savedOrder.getId(),
            savedOrder.getTotalAmount(),
            savedOrder.getStatus(),
            "payment-url"
        );
    }
}
