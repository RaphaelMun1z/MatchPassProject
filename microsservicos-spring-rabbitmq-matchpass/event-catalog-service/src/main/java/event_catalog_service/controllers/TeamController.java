package event_catalog_service.controllers;

import event_catalog_service.dtos.req.TeamRequestDTO;
import event_catalog_service.dtos.res.TeamResponseDTO;
import event_catalog_service.services.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/v1")
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody TeamRequestDTO dto) {
        return ResponseEntity.ok().body(teamService.createTeam(dto));
    }
}
