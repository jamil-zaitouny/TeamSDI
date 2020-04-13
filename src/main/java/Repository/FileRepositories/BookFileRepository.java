package Repository.FileRepositories;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Optional;

import static Repository.FileRepositories.FileUtilities.getCSVReader;
import static Repository.FileRepositories.FileUtilities.getCSVWriter;

public class BookFileRepository extends RepositoryInMemory<String, Book> {
    private String fileName;
    private String directory = "C:\\Users\\jamil\\Desktop\\TeamSDI\\src\\main\\java\\Files\\";
    public BookFileRepository(String directory){
        super();
        this.directory = directory;
        this.fileName = "books.xml";
        this.directory += this.fileName;
        loadCSV();
    }
    public BookFileRepository(String directory, String fileName){
        super();
        this.directory = directory;
        this.fileName = fileName;
        this.directory += this.fileName;
        loadCSV();
    }

    public void loadCSV(){
        try {
            CSVReader reader = getCSVReader(directory);
            reader
                    .readAll()
                    .forEach(v -> {
                        try {
                            this.add(new Book(v[0], v[1], v[2],v[3]));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            reader.close();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
    public String[] toCSV(Book book) {
        return (book.getId() + "," + book.getTitle() + "," + book.getAuthorName()+ "," + book.getGenre()).split(",") ;
    }

    public void saveCSV(){
        try {
            CSVWriter writer = getCSVWriter(directory);
            findAll()
                    .forEach(v -> writer.writeNext(toCSV(v)));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Book> add(Book entity) throws ValidatorException, IOException{
        Optional optional = super.add(entity);
        saveCSV();
        return optional;
    }

    @Override
    public Optional<Book> delete(String s) {
        Optional optional = super.delete(s);
        optional.ifPresent(v->saveCSV());
        return optional;
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException {
        Optional optional = super.update(entity);
        optional.ifPresent(v->saveCSV());
        return optional;
    }
}
