package vds.track.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@ImportResource("classpath:/vds/track/config/integration-context.xml")
public class IntegrationConfig {
}
