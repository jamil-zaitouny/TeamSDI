package springjpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"springjpa.Controller", "springjpa.Ui", "springjpa.Repository", "springjpa.Model"})
public class CatalogConfig {
}
