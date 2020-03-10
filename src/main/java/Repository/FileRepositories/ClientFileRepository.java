package Repository.FileRepositories;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static Repository.FileRepositories.FileUtilities.getCSVReader;
import static Repository.FileRepositories.FileUtilities.getCSVWriter;

public class ClientFileRepository extends RepositoryInMemory<Integer, Client> {
    private String fileName;
    private String directory;
    public ClientFileRepository(String directory){
        super();
        this.directory = directory;
        this.fileName = "clients.csv";
        this.directory += this.fileName;
        loadCSV();
    }
    public ClientFileRepository(String directory, String fileName){
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
                            this.add(new Client(Integer.parseInt(v[0]), v[1]));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    private void saveCSV(){
        try {
            CSVWriter writer = getCSVWriter(directory);
            findAll()
                    .forEach(v -> writer.writeNext(v.toCSV()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Client> add(Client entity) throws ValidatorException, IOException{
        Optional optional = super.add(entity);
        saveCSV();
        return optional;
    }

    @Override
    public Optional<Client> delete(Integer integer) {
        Optional optional = super.delete(integer);
        optional.ifPresent(v->saveCSV());
        return optional;
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException{
        Optional optional = super.update(entity);
        optional.ifPresent(v->saveCSV());
        return optional;
    }
}
