package springjpa.Model.Validators;

import org.springframework.stereotype.Component;
import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;

import java.util.Optional;
@Component
public class BookValidator implements IValidator<Book>{

    @Override
    public void validate(Book book) throws ValidatorException {
        Optional<Book> bookOptional = Optional.ofNullable(book);
        bookOptional.filter(v->v.getId().length() > 0).orElseThrow(() -> new ValidatorException("The ISBN should have at least 1 character"));
        bookOptional.filter(v->v.getTitle().length() < 100).orElseThrow(() -> new ValidatorException("The title cannot have more than 100 characters"));
        bookOptional.filter(v -> v.getAuthorName().length() < 100).orElseThrow(() -> new ValidatorException("The author name cannot have more than 100 characters"));
    }
}
