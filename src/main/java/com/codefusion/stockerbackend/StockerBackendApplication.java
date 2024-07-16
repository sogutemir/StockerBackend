package com.codefusion.stockerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StockerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockerBackendApplication.class, args);
    }

}
