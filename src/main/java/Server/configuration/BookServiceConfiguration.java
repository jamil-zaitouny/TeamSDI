package Server.configuration;

import Common.Communication.BookService;
import Model.Book;
import Repository.SortRepository.SortingRepository;
import Server.serviceimplementation.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;


@Configuration
@ComponentScan("Repository.DBRepository")
@ComponentScan("Controller")
public class BookServiceConfiguration {

    @Autowired
    private SortingRepository<String, Book> bookRepository;

    @Bean
    RmiServiceExporter rmiBookServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("BookController");
        rmiServiceExporter.setServiceInterface(BookService.class);
        rmiServiceExporter.setService(bookService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }

    @Bean
    BookService bookService(){
        SortingRepository<String, Book> sortingRepository = bookRepository;
        return new BookServiceImplementation(sortingRepository);
    }
}
