package Repository.FileRepositories;

import Model.Exceptions.FileException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Optional;

public class FileUtilities {
    public static CSVWriter getCSVWriter(String directory) throws IOException {
        File file = new File(directory);
        return new CSVWriter(new FileWriter(file));
    }

    public static CSVReader getCSVReader(String directory) throws FileNotFoundException {
        File file = new File(directory);
        Optional<File> optionalFile = Optional.ofNullable(file);
            return new CSVReader(new FileReader(
                    optionalFile
                            .filter(File::exists)
                            .orElseGet(() ->
                                    {
                                        try {
                                            file.createNewFile();
                                            return new File(directory);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        throw new NullPointerException("bla");
                                    }
                            )
            ));
    }
}