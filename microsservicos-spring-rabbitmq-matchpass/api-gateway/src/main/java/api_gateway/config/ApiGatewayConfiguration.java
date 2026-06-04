package api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p.path("/inventory-service/**").uri("lb://inventory-service"))
            .route(p -> p.path("/event-catalog-service/**").uri("lb://event-catalog-service"))
            .route(p -> p.path("/order-service/**").uri("lb://order-service"))
            .route(p -> p.path("/ticket-service/**").uri("lb://ticket-service"))
            .route(p -> p.path("/notification-service/**").uri("lb://notification-service"))
            .build();
    }
}
