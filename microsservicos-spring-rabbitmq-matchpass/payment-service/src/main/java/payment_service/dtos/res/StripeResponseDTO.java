package payment_service.dtos.res;

public record StripeResponseDTO(
    String status,
    String message,
    String sessionId,
    String sessionUrl
) {
}