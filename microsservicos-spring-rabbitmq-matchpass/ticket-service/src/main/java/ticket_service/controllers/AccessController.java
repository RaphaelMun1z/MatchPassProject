package ticket_service.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticket_service.dtos.req.ValidateAccessRequestDTO;
import ticket_service.dtos.res.AccessValidationResponse;
import ticket_service.entities.AccessLog;
import ticket_service.services.AccessService;

@RestController
@RequestMapping("/ticket-service/api/ticket/access")
public class AccessController {
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/v1/validate")
    public ResponseEntity<AccessValidationResponse> validateAccess(
        @RequestBody ValidateAccessRequestDTO dto
    ) {
        return ResponseEntity.ok(accessService.validateTicket(dto));
    }

    @GetMapping("/v1/logs")
    public ResponseEntity<Page<AccessLog>> getAccessLogs(
        @RequestParam(required = false) String eventId,
        @RequestParam(required = false) String gateId,
        @RequestParam(required = false) String result,
        Pageable pageable) {
        return ResponseEntity.ok(accessService.findLogs(eventId, gateId, result, pageable));
    }
}
