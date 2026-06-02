package order_service.controllers;

import order_service.dtos.req.CheckoutRequestDTO;
import order_service.dtos.res.OrderSummaryResponseDTO;
import order_service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderSummaryResponseDTO> checkout(
        @RequestBody CheckoutRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.processCheckout(dto));
    }
}
