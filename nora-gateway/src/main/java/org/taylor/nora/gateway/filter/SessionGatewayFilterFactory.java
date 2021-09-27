package org.taylor.nora.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class SessionGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Object config) {
        return new innerFilter(config);
    }


    private class innerFilter implements GatewayFilter {

        private Object config;

        public innerFilter(Object config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            //由于webflux是非阻塞式编程，切记此处采用非阻塞方式获取Mono里面的对象，采用block()会抛出异常
            exchange.getSession().doOnNext(res -> res.getAttributes().put("userDto","longjie")).subscribe();   //修改session时间
            return chain.filter(exchange);
        }
    }

}
