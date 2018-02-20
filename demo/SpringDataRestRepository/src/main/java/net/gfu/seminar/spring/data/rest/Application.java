package net.gfu.seminar.spring.data.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// TODO for boot 1.4+ import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by tf on 27.11.14.
 */
@Configuration
@EnableJpaRepositories
@Import({RepositoryRestMvcConfiguration.class, DataSourceConfig.class})
@EntityScan(basePackages={"net.gfu.seminar.spring.batch.guestimport"})
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run( Application.class, args );
    }
}
