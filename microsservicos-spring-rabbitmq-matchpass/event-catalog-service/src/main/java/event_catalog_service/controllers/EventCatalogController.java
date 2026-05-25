package event_catalog_service.controllers;

import event_catalog_service.dtos.req.CreateVenueRequestDTO;
import event_catalog_service.dtos.res.CreatedVenueResponseDTO;
import event_catalog_service.services.EventCatalogService;
import event_catalog_service.services.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event-catalog")
public class EventCatalogController {
    private final EventCatalogService eventCatalogService;
    private final VenueService venueService;

    public EventCatalogController(EventCatalogService eventCatalogService, VenueService venueService) {
        this.eventCatalogService = eventCatalogService;
        this.venueService = venueService;
    }

    @GetMapping("/v1/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @PostMapping("/v1/venue")
    public CreatedVenueResponseDTO createVenue(CreateVenueRequestDTO dto) {
        return venueService.createVenue(dto);
    }
}
