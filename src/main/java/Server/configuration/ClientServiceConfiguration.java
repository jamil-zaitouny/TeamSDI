package Server.configuration;

import Common.Communication.ClientService;
import Model.Client;
import Repository.DBRepository.ClientDBRepository;
import Repository.SortRepository.SortingRepository;
import Server.serviceimplementation.ClientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan("Repository.DBRepository")
@ComponentScan("Controller")
public class ClientServiceConfiguration {
    @Autowired
    ClientDBRepository clientRepository;

    @Bean
    RmiServiceExporter rmiClientServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ClientController");
        rmiServiceExporter.setServiceInterface(ClientService.class);
        rmiServiceExporter.setService(clientService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }
    @Bean
    public ClientService clientService(){
        SortingRepository<Integer, Client> sortingRepository = clientRepository;
        return new ClientServiceImplementation(sortingRepository);
    }
}
