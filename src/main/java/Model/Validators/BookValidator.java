package Model.Validators;

import Model.Book;
import Model.Client;
import Model.Exceptions.ValidatorException;
import javafx.util.Pair;

import java.util.List;

public class BookValidator implements IValidator{

    @Override
    public void validate(Object o) throws ValidatorException {
        if(o instanceof Pair){
            if(
                    ((Pair<List, Book>) o)
                            .getKey()
                            .stream()
                            .noneMatch(v -> v.equals(((Pair<List, Book>) o).getValue())
                            )
            ){
                return;
            }
        }
        throw new ValidatorException("The book already exists in the database!");
    }
}
