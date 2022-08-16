 package com.in28minutes.microservices.apigateway.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Service;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Mono;

// Logs every request that goes through the API Gateway
@Service
public class LoggingFilter implements GlobalFilter{
    
    @Autowired(required =  false)
    private Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        
        logger.info("Path of the request recieved -> {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
  