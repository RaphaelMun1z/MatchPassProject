package event_catalog_service.controllers;

import event_catalog_service.dtos.req.CreateVenueRequestDTO;
import event_catalog_service.dtos.req.SectorRequestDTO;
import event_catalog_service.dtos.res.SectorResponseDTO;
import event_catalog_service.dtos.res.VenueResponseDTO;
import event_catalog_service.services.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venue")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/v1")
    public ResponseEntity<List<VenueResponseDTO>> getAllVenues() {
        return ResponseEntity.ok().body(venueService.getAllVenues());
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<VenueResponseDTO> getVenueById(
        @PathVariable String id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @GetMapping("/v1/filter-by-location")
    public ResponseEntity<List<VenueResponseDTO>> getVenuesByLocation(
        @RequestParam String city,
        @RequestParam String state) {
        return ResponseEntity.ok(venueService.getVenuesByLocation(city, state));
    }

    @PostMapping("/v1")
    public ResponseEntity<VenueResponseDTO> createVenue(
        @RequestBody CreateVenueRequestDTO dto) {
        return ResponseEntity.ok(venueService.createVenue(dto));
    }

    @PostMapping("/v1/{id}/add-sector")
    public ResponseEntity<SectorResponseDTO> addSectorToVenue(
        @PathVariable String id,
        @RequestBody SectorRequestDTO dto) {
        return ResponseEntity.ok(venueService.addSectorToVenue(dto, id));
    }

    @DeleteMapping("/v1/{venueId}/remove-sector/{sectorId}")
    public ResponseEntity<Void> removeSectorFromVenue(
        @PathVariable String venueId,
        @PathVariable String sectorId
    ) {
        venueService.removeSectorFromVenue(venueId, sectorId);
        return ResponseEntity.noContent().build();
    }
}
