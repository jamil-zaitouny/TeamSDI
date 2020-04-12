package Client.config;

import Common.Communication.BookService;
import Common.Communication.ClientService;
import Common.Communication.PurchaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;


@Configuration
public class ClientConfig {
    @Bean
    RmiProxyFactoryBean rmiBookProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(BookService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/BookController");
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiClientProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ClientService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/ClientController");
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiPurchaseProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(PurchaseService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/PurchaseController");
        return rmiProxyFactoryBean;
    }
}

