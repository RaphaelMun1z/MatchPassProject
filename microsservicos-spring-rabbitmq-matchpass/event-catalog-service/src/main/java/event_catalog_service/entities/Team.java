package event_catalog_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return id != null && id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
