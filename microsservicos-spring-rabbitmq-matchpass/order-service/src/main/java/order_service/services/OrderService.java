package order_service.services;

import feign.FeignException;
import order_service.dtos.req.CheckoutRequestDTO;
import order_service.dtos.req.OrderItemRequestDTO;
import order_service.dtos.res.OrderItemResponseDTO;
import order_service.dtos.res.OrderResponseDTO;
import order_service.dtos.res.OrderSummaryResponseDTO;
import order_service.entities.Order;
import order_service.entities.OrderItem;
import order_service.entities.enums.OrderStatusEnum;
import order_service.exceptions.models.NotFoundException;
import order_service.proxy.InventoryProxy;
import order_service.proxy.SeatStatusResponseDTO;
import order_service.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final InventoryProxy inventoryProxy;

    public OrderService(OrderRepository orderRepository, InventoryProxy inventoryProxy) {
        this.orderRepository = orderRepository;
        this.inventoryProxy = inventoryProxy;
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
            OrderStatusEnum.PENDING
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

        logger.info("Antes do try");
        // Bloquear assento temporariamente
        try {
            logger.info("Entrou no try");
            List<SeatStatusResponseDTO> seatsStatus = savedOrder.getItems()
                .stream()
                .map(orderItem -> inventoryProxy.tryLockSeat(orderItem.getSeatTag(), savedOrder.getUserId())).toList();
        } catch (FeignException.NotFound ex) {
            logger.error(ex.getMessage());
            throw new NotFoundException("Não foi possível realizar a reserva dos ingressos.");
        } catch (Exception ex) {
            logger.error("Outro erro: {}", ex.getMessage());
        }
        logger.info("Depois do try");

        return new OrderSummaryResponseDTO(
            savedOrder.getId(),
            savedOrder.getTotalAmount(),
            savedOrder.getStatus(),
            "payment-url"
        );
    }

    public OrderResponseDTO findProcessById(String processId) {
        Order order = orderRepository.findById(processId).orElseThrow(
            () -> new NotFoundException("Nenhum pedido encontrado.")
        );
        List<OrderItemResponseDTO> orderItems = order.getItems()
            .stream().map(oi -> new OrderItemResponseDTO(
                oi.getId(),
                oi.getSectorId(),
                oi.getSeatTag(),
                oi.getTicketType(),
                oi.getAppliedPrice()
            )).toList();
        return new OrderResponseDTO(
            order.getId(),
            order.getTotalAmount(),
            order.getStatus(),
            "payment-url",
            orderItems
        );
    }

    public List<OrderSummaryResponseDTO> findProcessByEventId(String eventId) {
        List<Order> orders = orderRepository.findByEventId(eventId);
        return orders.stream().map(order -> new OrderSummaryResponseDTO(
            order.getId(),
            order.getTotalAmount(),
            order.getStatus(),
            "payment-url"
        )).toList();
    }

    public OrderSummaryResponseDTO findProcessBySeatTag(String seatTag) {
        Order order = orderRepository.findByItemsSeatTag(seatTag).orElseThrow(
            () -> new NotFoundException("Nenhum pedido encontrado.")
        );
        return new OrderSummaryResponseDTO(
            order.getId(),
            order.getTotalAmount(),
            order.getStatus(),
            "payment-url"
        );
    }

    @Transactional
    public OrderSummaryResponseDTO updateProcessStatus(String orderId, OrderStatusEnum orderStatusEnum) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("Nenhum pedido encontrado.")
        );
        order.updateStatus(orderStatusEnum);
        return new OrderSummaryResponseDTO(
            order.getId(),
            order.getTotalAmount(),
            order.getStatus(),
            "payment-url"
        );
    }

    public List<OrderSummaryResponseDTO> findOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
            .map(order -> new OrderSummaryResponseDTO(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                "payment-url"
            )).toList();
    }
}
