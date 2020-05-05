package client;

import client.ui.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("client.config");
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        Console console=new Console(restTemplate);
        console.run();
    }
}
