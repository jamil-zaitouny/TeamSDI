import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springjpa.Ui.Console;


public class Main {

    public static void main(String[] args) {
        System.out.println("Application Running!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "springjpa.config"
        );
        context.getBean(Console.class).run();
    }
}