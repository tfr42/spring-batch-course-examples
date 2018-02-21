package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Reads the Spring Integration configuration from XML file.
 */
@Configuration
@ImportResource( "classpath:batch-integration-config.xml")
public class IntegrationConfiguration {

}
