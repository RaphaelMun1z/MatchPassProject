package event_catalog_service.services;

import event_catalog_service.dtos.req.CreateVenueRequestDTO;
import event_catalog_service.dtos.req.SectorRequestDTO;
import event_catalog_service.dtos.res.SectorPricingResponseDTO;
import event_catalog_service.dtos.res.SectorResponseDTO;
import event_catalog_service.dtos.res.VenueResponseDTO;
import event_catalog_service.entities.Sector;
import event_catalog_service.entities.Venue;
import event_catalog_service.repositories.SectorRepository;
import event_catalog_service.repositories.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final SectorRepository sectorRepository;

    public VenueService(VenueRepository venueRepository, SectorRepository sectorRepository) {
        this.venueRepository = venueRepository;
        this.sectorRepository = sectorRepository;
    }

    public List<VenueResponseDTO> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        List<VenueResponseDTO> createdVenues = venues
            .stream()
            .map(v -> {
                List<SectorResponseDTO> sectorsDTO = v.getSectors()
                    .stream()
                    .map(s ->
                        new SectorResponseDTO(
                            s.getId(),
                            s.getName(),
                            s.getHasNumberedSeats()
                        )).toList();

                VenueResponseDTO venueDTO =
                    new VenueResponseDTO(
                        v.getId(),
                        v.getName(),
                        v.getCity(),
                        v.getState(),
                        v.getTotalCapacity(),
                        sectorsDTO
                    );
                return venueDTO;
            }).toList();
        return createdVenues;
    }

    @Transactional
    public VenueResponseDTO createVenue(CreateVenueRequestDTO dto) {
        validateTotalCapacity(dto);

        // Cria e Salva a instância de Venue
        Venue newVenue = new Venue(
            dto.name(),
            dto.city(),
            dto.state(),
            dto.totalCapacity()
        );
        Venue savedVenue = venueRepository.save(newVenue);

        List<SectorResponseDTO> sectorsDTO = dto.sectors()
            .stream()
            .map(s -> {
                // Cria e Salva uma instância de Sector
                Sector newSector = new Sector(
                    savedVenue,
                    s.name(),
                    s.capacity(),
                    s.hasNumberedSeats()
                );
                Sector savedSector = sectorRepository.save(newSector);

                // Transforma Sector em ResponseDTO
                return new SectorResponseDTO(
                    savedSector.getId(),
                    savedSector.getName(),
                    savedSector.getHasNumberedSeats()
                );
            }).toList();

        // Transforma Venue em ResponseDTO
        return new VenueResponseDTO(
            savedVenue.getId(),
            savedVenue.getName(),
            savedVenue.getCity(),
            savedVenue.getState(),
            savedVenue.getTotalCapacity(),
            sectorsDTO
        );
    }

    private void validateTotalCapacity(CreateVenueRequestDTO dto) {
        int totalCapacityExpected = dto.totalCapacity();
        int totalCapacityCount = dto.sectors().stream().mapToInt(SectorRequestDTO::capacity).sum();
        if (totalCapacityCount != totalCapacityExpected) {
            throw new IllegalArgumentException("A capacidade total informada e a soma das capacidades dos setores não são iguais.");
        }
    }

    public VenueResponseDTO getVenueById(String id) {
        Optional<Venue> venueFound = venueRepository.findById(id);
        if (venueFound.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse identificador");
        }

        List<SectorResponseDTO> sectorsDTO =
            venueFound.get().getSectors()
                .stream()
                .map(s -> new SectorResponseDTO(
                    s.getId(),
                    s.getName(),
                    s.getHasNumberedSeats()
                )).toList();

        return new VenueResponseDTO(
            venueFound.get().getId(),
            venueFound.get().getName(),
            venueFound.get().getCity(),
            venueFound.get().getState(),
            venueFound.get().getTotalCapacity(),
            sectorsDTO
        );
    }

    @Transactional
    public SectorResponseDTO addSectorToVenue(SectorRequestDTO sectorDTO, String venueId) {
        Optional<Venue> venueFound = venueRepository.findById(venueId);
        if (venueFound.isEmpty()) {
            throw new IllegalArgumentException("O local indicado não foi encontrado.");
        }

        Sector newSector = new Sector(
            venueFound.get(),
            sectorDTO.name(),
            sectorDTO.capacity(),
            sectorDTO.hasNumberedSeats()
        );
        Sector savedSector = sectorRepository.save(newSector);

        venueFound.get().addSector(savedSector);

        return new SectorResponseDTO(
            savedSector.getId(),
            savedSector.getName(),
            savedSector.getHasNumberedSeats()
        );
    }
}
