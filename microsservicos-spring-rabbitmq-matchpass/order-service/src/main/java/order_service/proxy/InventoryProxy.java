package order_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inventory-service")
public interface InventoryProxy {
    @PostMapping("/api/inventory/lock-seat/{seatTag}/user/{userId}")
    SeatStatusResponseDTO tryLockSeat(@PathVariable String seatTag, @PathVariable String userId);
}
