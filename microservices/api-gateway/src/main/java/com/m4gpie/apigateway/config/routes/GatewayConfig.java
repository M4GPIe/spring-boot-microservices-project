package com.m4gpie.apigateway.config.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

        @Autowired
        private JWTAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route(r -> r.path("/api/product/**")
                                                .filters(f -> f.filter(jwtAuthenticationFilter))
                                                .uri("lb://product-service"))

                                .route(r -> r.path("/api/inventory/**")
                                                .filters(f -> f.filter(jwtAuthenticationFilter))
                                                .uri("lb://inventory-service"))

                                .route(r -> r.path("/api/order/**")
                                                .filters(f -> f.filter(jwtAuthenticationFilter))
                                                .uri("lb://order-service"))

                                .route(r -> r.path("/eureka/web")
                                                .filters(f -> f.setPath("/"))
                                                .uri("http://localhost:8761"))

                                .route(r -> r.path("/eureka/**")
                                                .uri("http://localhost:8761"))

                                .route(r -> r.path("/eureka/web")
                                                .filters(f -> f.setPath("/"))
                                                .uri("http://localhost:8761"))

                                .route(r -> r.path("/auth/**")
                                                .uri("lb://auth-service"))

                                .build();
        }
}
