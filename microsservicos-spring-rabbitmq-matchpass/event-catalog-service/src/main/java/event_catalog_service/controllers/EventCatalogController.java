package event_catalog_service.controllers;

import event_catalog_service.dtos.req.CreateEventRequestDTO;
import event_catalog_service.dtos.req.SectorPricingRequestDTO;
import event_catalog_service.dtos.res.EventDetailsResponseDTO;
import event_catalog_service.dtos.res.SectorPricingResponseDTO;
import event_catalog_service.services.EventCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventCatalogController {
    private final EventCatalogService eventCatalogService;

    public EventCatalogController(EventCatalogService eventCatalogService) {
        this.eventCatalogService = eventCatalogService;
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<EventDetailsResponseDTO> findEventById(@PathVariable String id) {
        return ResponseEntity.ok().body(eventCatalogService.findEventById(id));
    }

    @PostMapping("/v1")
    public ResponseEntity<EventDetailsResponseDTO> createEvent(@RequestBody CreateEventRequestDTO dto) {
        return ResponseEntity.ok().body(eventCatalogService.registerEvent(dto));
    }

    @PostMapping("/v1/{id}/add-sector")
    public ResponseEntity<SectorPricingResponseDTO> addSectorToAnEvent(@PathVariable String id, @RequestBody SectorPricingRequestDTO dto) {
        return ResponseEntity.ok().body(eventCatalogService.addSectorToAnEvent(id, dto));
    }

    @DeleteMapping("/v1/{id}/remove-sector/{secId}")
    public ResponseEntity<Void> removeSectorFromAnEvent(
        @PathVariable String id,
        @PathVariable String secId) {
        eventCatalogService.removeSectorFromAnEvent(id, secId);
        return ResponseEntity.noContent().build();
    }
}