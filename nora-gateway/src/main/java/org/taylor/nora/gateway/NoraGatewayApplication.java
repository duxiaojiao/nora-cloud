package org.taylor.nora.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class NoraGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NoraGatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
