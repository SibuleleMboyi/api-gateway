package com.in28minutes.microservices.apigateway.configurations;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        //Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get").uri("http://httpbin.org:80");


        return builder.routes()
            .route(p -> p.path("/get").uri("http://httpbin.org:80"))
            .route(p-> p.path("/jpa-currency-exchange/**").uri("lb://currency-exchange"))
            .route(p-> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
            .route(p-> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
            .route(p-> p.path("/currency-conversion-overwrite_url/**") // add new custom URL
                .filters(f -> f.rewritePath("/currency-conversion-overwrite_url/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
                .uri("lb://currency-conversion"))
            .build();
    }
}
