package Server.configuration;

import Repository.DBRepository.BookDBRepository;
import Repository.DBRepository.ClientDBRepository;
import Repository.DBRepository.PurchaseDBRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public BookDBRepository bookDBRepository(){
        return new BookDBRepository();
    }

    @Bean
    public ClientDBRepository clientDBRepository(){
        return new ClientDBRepository();
    }

    @Bean
    public PurchaseDBRepository purchaseDBRepository(){
        return new PurchaseDBRepository();
    }
}
