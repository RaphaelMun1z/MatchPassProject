package event_catalog_service.services;

import event_catalog_service.dtos.req.CreateVenueRequestDTO;
import event_catalog_service.dtos.req.SectorRequestDTO;
import event_catalog_service.dtos.res.CreatedVenueResponseDTO;
import event_catalog_service.dtos.res.SectorPricingResponseDTO;
import event_catalog_service.entities.Sector;
import event_catalog_service.entities.Venue;
import event_catalog_service.repositories.SectorRepository;
import event_catalog_service.repositories.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final SectorRepository sectorRepository;

    public VenueService(VenueRepository venueRepository, SectorRepository sectorRepository) {
        this.venueRepository = venueRepository;
        this.sectorRepository = sectorRepository;
    }

    @Transactional
    public CreatedVenueResponseDTO createVenue(CreateVenueRequestDTO dto) {
        validateTotalCapacity(dto);

        Venue newVenue = new Venue(
            dto.name(),
            dto.city(),
            dto.state(),
            dto.totalCapacity()
        );
        Venue savedVenue = venueRepository.save(newVenue);

        List<SectorPricingResponseDTO> savedSectorsDTO = dto.sectors().stream().map(s -> {
            Sector newSector = new Sector(
                savedVenue,
                s.name(),
                s.capacity(),
                s.basePrice(),
                s.halfPrice(),
                s.hasNumberedSeats()
            );

            Sector savedSector = sectorRepository.save(newSector);
            return new SectorPricingResponseDTO(
                savedSector.getId(),
                savedSector.getName(),
                savedSector.getBasePrice(),
                savedSector.getHalfPrice(),
                savedSector.getHasNumberedSeats()
            );
        }).toList();

        return new CreatedVenueResponseDTO(
            savedVenue.getId(),
            savedVenue.getName(),
            savedVenue.getCity(),
            savedVenue.getState(),
            savedVenue.getTotalCapacity(),
            savedSectorsDTO
        );
    }

    private void validateTotalCapacity(CreateVenueRequestDTO dto) {
        int totalCapacityExpected = dto.totalCapacity();
        int totalCapacityCount = dto.sectors().stream().mapToInt(SectorRequestDTO::capacity).sum();
        if(totalCapacityCount != totalCapacityExpected) {
            throw new IllegalArgumentException("A capacidade total informada e a soma das capacidades dos setores não são iguais.");
        }
    }
}
