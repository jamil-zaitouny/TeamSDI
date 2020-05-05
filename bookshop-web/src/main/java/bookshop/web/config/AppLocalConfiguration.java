package bookshop.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import bookshop.core.configuration.JPAConfig;

@Configuration
@ComponentScan({"bookshop.core"})
@Import({JPAConfig.class})
@PropertySources({@PropertySource("classpath:/db.properties")})
public class AppLocalConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
