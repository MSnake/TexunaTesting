package texuna.test.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import texuna.test.models.UserModel;
import texuna.test.util.SourceDataParser;


/**
 * Тестирование парсера входных данных
 * @author MSnake
 *
 */
public class DataParserTest {

    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        File sourceDataFile = new File("data/source-data.tsv");
        SourceDataParser parser = new SourceDataParser(sourceDataFile);
        ArrayList<UserModel> model = parser.createDataUser();
        System.out.println("");

    }

}
