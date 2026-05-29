package event_catalog_service.services;

import event_catalog_service.dtos.req.CreateVenueRequestDTO;
import event_catalog_service.dtos.req.SectorRequestDTO;
import event_catalog_service.dtos.res.SectorResponseDTO;
import event_catalog_service.dtos.res.VenueResponseDTO;
import event_catalog_service.entities.Sector;
import event_catalog_service.entities.Venue;
import event_catalog_service.repositories.SectorRepository;
import event_catalog_service.repositories.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

                return venueObjToVenueResDTO(v, sectorsDTO);
            }).toList();
        return createdVenues;
    }

    public VenueResponseDTO getVenueById(String id) {
        Venue venueFound = findVenueById(id);

        List<SectorResponseDTO> sectorsDTO =
            venueFound.getSectors()
                .stream()
                .map(s -> new SectorResponseDTO(
                    s.getId(),
                    s.getName(),
                    s.getHasNumberedSeats()
                )).toList();

        return venueObjToVenueResDTO(venueFound, sectorsDTO);
    }

    public List<VenueResponseDTO> getVenuesByLocation(String city, String state) {
        List<Venue> venues = venueRepository.findByCityIgnoreCaseAndStateIgnoreCase(city, state);

        List<VenueResponseDTO> venuesDTO = venues
            .stream()
            .map(v -> venueObjToVenueResDTO(v, null))
            .toList();

        return venuesDTO;
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
        return venueObjToVenueResDTO(savedVenue, sectorsDTO);
    }

    @Transactional
    public SectorResponseDTO addSectorToVenue(SectorRequestDTO sectorDTO, String venueId) {
        Venue venueFound = findVenueById(venueId);

        Sector newSector = new Sector(
            venueFound,
            sectorDTO.name(),
            sectorDTO.capacity(),
            sectorDTO.hasNumberedSeats()
        );
        Sector savedSector = sectorRepository.save(newSector);

        venueFound.addSector(savedSector);

        return new SectorResponseDTO(
            savedSector.getId(),
            savedSector.getName(),
            savedSector.getHasNumberedSeats()
        );
    }

    @Transactional
    public void removeSectorFromVenue(String venueId, String sectorId) {
        Venue venueFound = findVenueById(venueId);
        Sector sectorToRemove = venueFound.getSectors()
            .stream()
            .filter(s -> s.getId().equals(sectorId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Setor com ID " + sectorId + " não encontrado no venue " + venueId));

        venueFound.removeSector(sectorToRemove);
    }

    // Métodos privados

    private Venue findVenueById(String id) {
        return venueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Setor não encontrado com o ID: " + id));
    }

    private void validateTotalCapacity(CreateVenueRequestDTO dto) {
        int totalCapacityExpected = dto.totalCapacity();
        int totalCapacityCount = dto.sectors().stream().mapToInt(SectorRequestDTO::capacity).sum();
        if (totalCapacityCount != totalCapacityExpected) {
            throw new IllegalArgumentException("A capacidade total informada e a soma das capacidades dos setores não são iguais.");
        }
    }

    private VenueResponseDTO venueObjToVenueResDTO(Venue venue, List<SectorResponseDTO> sectorsResDTO) {
        return new VenueResponseDTO(
            venue.getId(),
            venue.getName(),
            venue.getCity(),
            venue.getState(),
            venue.getTotalCapacity(),
            sectorsResDTO
        );
    }
}
