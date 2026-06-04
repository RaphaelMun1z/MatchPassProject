package ticket_service.entities;


import jakarta.persistence.*;
import ticket_service.entities.enums.AccessResultEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_logs")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String ticketId;
    private String gateId;
    private LocalDateTime accessedAt;

    @Enumerated(EnumType.STRING)
    private AccessResultEnum result;

    public AccessLog() {
    }

    public AccessLog(String ticketId, String gateId, LocalDateTime accessedAt, AccessResultEnum result) {
        this.ticketId = ticketId;
        this.gateId = gateId;
        this.accessedAt = accessedAt;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getGateId() {
        return gateId;
    }

    public LocalDateTime getAccessedAt() {
        return accessedAt;
    }

    public AccessResultEnum getResult() {
        return result;
    }
}
