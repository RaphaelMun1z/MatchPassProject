package payment_service.dtos.req;

public record ProductRequestDTO(
    Long amount,
    Long quantity,
    String name,
    String currency
) {
}
