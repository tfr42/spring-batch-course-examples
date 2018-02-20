package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by tf on 28.11.14.
 */
@Configuration
@ImportResource( "classpath:batch-integration-config.xml")
public class IntegrationConfiguration {

}
