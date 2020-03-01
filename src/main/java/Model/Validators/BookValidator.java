package Model.Validators;

import Model.Book;
import Model.Client;
import Model.Exceptions.ValidatorException;
import javafx.util.Pair;

import java.util.List;

public class BookValidator implements IValidator<Book>{

    @Override
    public void validate(Book book) throws ValidatorException {
        if(!(book.getId().length() == 13)){
            throw new ValidatorException("The ISBN should be 13 character's long");
        }else if(book.getTitle().length() > 100){
            throw new ValidatorException("The title cannot have more than 100 characters");
        }else if(book.getAuthorName().length() >100){
            throw new ValidatorException("The author name cannot be more than 100 characters long!");
        }
    }
}
