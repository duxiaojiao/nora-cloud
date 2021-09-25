package org.taylor.nora.job;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "org.taylor.nora.common.feign")
@SpringBootApplication(scanBasePackages = "org.taylor.*")
public class NoraJobApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NoraJobApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
