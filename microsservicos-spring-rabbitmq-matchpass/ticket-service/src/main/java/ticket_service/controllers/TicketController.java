package ticket_service.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticket_service.dtos.req.GenerateTicketRequestDTO;
import ticket_service.entities.Ticket;
import ticket_service.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/v1")
    public ResponseEntity<List<Ticket>> generateTickets(
        @RequestBody GenerateTicketRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.generateFromOrder(dto));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<Ticket> getTicketById(
        @PathVariable String id
    ) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUser(
        @PathVariable String userId
    ) {
        return ResponseEntity.ok(ticketService.findByUserId(userId));
    }

    @GetMapping("/v1/event/{eventId}")
    public ResponseEntity<Page<Ticket>> getTicketsByEvent(
        @PathVariable String eventId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(ticketService.findByEventId(eventId, pageable));
    }

    @PatchMapping("/v1/{id}/revoke")
    public ResponseEntity<Void> revokeTicket(
        @PathVariable String id
    ) {
        ticketService.revokeTicket(id);
        return ResponseEntity.noContent().build();
    }
}
