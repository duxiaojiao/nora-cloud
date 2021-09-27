package org.taylor.nora.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import feign.form.util.CharsetUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JsonServerLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        ServerHttpResponse response = exchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        String result = JSONObject.toJSONString("注销成功");
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(CharsetUtil.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
