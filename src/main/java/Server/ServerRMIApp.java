package Server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServerRMIApp {
    public static void main(String args[]){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Server.config");
        System.out.println("Server started");

    }
}
