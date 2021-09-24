package org.taylor.nora.auth;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NoraAuthApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NoraAuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
