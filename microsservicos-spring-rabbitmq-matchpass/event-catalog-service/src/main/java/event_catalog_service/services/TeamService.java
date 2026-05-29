package event_catalog_service.services;

import event_catalog_service.dtos.req.TeamRequestDTO;
import event_catalog_service.dtos.res.TeamResponseDTO;
import event_catalog_service.entities.Team;
import event_catalog_service.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamResponseDTO createTeam(TeamRequestDTO dto) {
        Team newTeam = new Team(dto.name());
        Team savedTeam = teamRepository.save(newTeam);
        return new TeamResponseDTO(
            savedTeam.getId(),
            savedTeam.getName()
        );
    }
}
