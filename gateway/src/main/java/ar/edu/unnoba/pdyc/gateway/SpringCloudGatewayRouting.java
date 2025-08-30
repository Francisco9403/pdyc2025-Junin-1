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
                .route(r -> r.path("/api/songs/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://song-service"))
                
                .route(r -> r.path("/api/playlists/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://playlist-service"))
                .build();
    }
}
