package ar.edu.unnoba.pdyc.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/admin/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://artist-service"))
                .route(r -> r.path("/api/public/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://artist-service"))                        
                


                .route(r -> r.path("/api/admins/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://auth-admin-user-service"))
                .route(r -> r.path("/api/users/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://auth-admin-user-service"))
                .route(r -> r.path("/api/public/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://auth-admin-user-service"))



                .route(r -> r.path("/api/admin/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://event-service"))
        
                .build();
    }
}
