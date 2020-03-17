package Repository.FileRepositories;

import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Repository.RepositoryInMemory;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static Repository.FileRepositories.FileUtilities.getCSVReader;
import static Repository.FileRepositories.FileUtilities.getCSVWriter;

public class PurchaseFileRepository extends RepositoryInMemory<Integer, Purchase> {
    private String fileName;
    private String directory;
    public PurchaseFileRepository(String directory){
        super();
        this.directory = directory;
        this.fileName = "Purchases.csv";
        this.directory += this.fileName;
        loadCSV();
    }
    public String[] toCSV(Purchase purchase) {
        return (purchase.getId() + "," +purchase.getBookId() + "," +purchase.getClientId()+","+purchase.getPurcahseDetails()).split(",");
    }
    public PurchaseFileRepository(String directory, String fileName){
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
                            this.add(new Purchase(Integer.parseInt(v[0]), v[1], Integer.parseInt(v[2]),v[3]));
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
    public Optional<Purchase> add(Purchase entity) throws ValidatorException, IOException{
        Optional optional = super.add(entity);
        saveCSV();
        return optional;
    }

    @Override
    public Optional<Purchase> delete(Integer integer)  {
        Optional optional = super.delete(integer);
        optional.ifPresent(v->saveCSV());
        return optional;
    }

    @Override
    public Optional<Purchase> update(Purchase entity) throws ValidatorException {
        Optional optional = super.update(entity);
        optional.ifPresent(v->saveCSV());
        return optional;
    }
}
