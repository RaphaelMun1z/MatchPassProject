package inventory_service.controllers;

import inventory_service.dtos.req.SeatReservationRequestDTO;
import inventory_service.dtos.res.SeatStatusResponseDTO;
import inventory_service.environment.InstanceInformationService;
import inventory_service.services.InventoryManagementService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
public class InventoryManagementController {
    private final InstanceInformationService informationService;
    private final InventoryManagementService inventoryManagementService;

    public InventoryManagementController(InstanceInformationService informationService, InventoryManagementService inventoryManagementService) {
        this.informationService = informationService;
        this.inventoryManagementService = inventoryManagementService;
    }

    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping(
        value = "/lock-seat/event/{eventId}/sector/{sectorId}/seat/{seatTag}/user/{userId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SeatStatusResponseDTO tryLockSeat(
        @PathVariable String eventId,
        @PathVariable String sectorId,
        @PathVariable String seatTag,
        @PathVariable String userId) {
        SeatReservationRequestDTO dto = new SeatReservationRequestDTO(
            eventId,
            sectorId,
            seatTag
        );
        return inventoryManagementService.tryLockSeat(
            dto,
            userId,
            "PORT " + informationService.retrieveServerPort()
        );
    }

    @GetMapping(
        value = "/check/{lockId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SeatStatusResponseDTO checkSeatStatus(@PathVariable String lockId) {
        return inventoryManagementService.checkSeatStatus(
            lockId,
            "PORT " + informationService.retrieveServerPort()
        );
    }

    @PostMapping("/confirm/{lockId}")
    public void confirmSeatSold(@PathVariable String lockId) {
        inventoryManagementService.confirmSeatSold(lockId);
    }

    @DeleteMapping("/release/{lockId}")
    public void releaseSeat(@PathVariable String lockId) {
        inventoryManagementService.releaseSeat(lockId);
    }
}
