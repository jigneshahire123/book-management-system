
package com.example.gatewayserver;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyPreFilter implements GlobalFilter {

	private static final Logger LOG = LoggerFactory.getLogger(MyPreFilter.class);

	@Autowired
	ReactiveJwtDecoder jwtDecoder;
	
	private String user = null;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		LOG.info("#### Apigateway Prefilter called #### {}", exchange.getRequest().getHeaders());
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//String currentPrincipalName = authentication.getName();
		String authToken = exchange.getRequest().getHeaders().getFirst("Authorization");
		authToken = authToken.replace("Bearer ", "");
		Mono<Jwt> decoded = jwtDecoder.decode(authToken);
		LOG.info("#### Apigateway Prefilter decoded #### {}", decoded.map(Jwt::getSubject));
		Mono<Map<String, Object>> subject = decoded.map(Jwt::getClaims);
		subject.subscribe(sub ->  getUserName(sub));
		ServerHttpRequest request = exchange.getRequest().mutate().header("custId", user).build();
		ServerWebExchange exchange1 = exchange.mutate().request(request).build();
		return chain.filter(exchange1);
	}

	private Object getUserName(Map<String, Object> sub) {
		user = sub.get("user_name").toString();
		return sub.get("user_name");
	}
}
