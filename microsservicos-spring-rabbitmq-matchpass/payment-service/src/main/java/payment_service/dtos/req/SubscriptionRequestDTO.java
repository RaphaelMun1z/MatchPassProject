package payment_service.dtos.req;

import payment_service.entities.enums.PlanTypeEnum;

public record SubscriptionRequestDTO(
    PlanTypeEnum planType
) {
}
