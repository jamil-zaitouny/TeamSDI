package springjpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"springjpa.Repository","springjpa.Model.Validators","springjpa.Controller", "springjpa.Ui"})
public class CatalogConfig {
}
