import bookshop.web.dto.Model.BookDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

public class ClientApp {
    public static final String URL = "http://localhost:8080/api/books";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "bookshop.client.config"
                );
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        restTemplate.postForObject(
                URL,
                new BookDto("1", "2", "3", "4"),
                BookDto.class
        );

    }
}
