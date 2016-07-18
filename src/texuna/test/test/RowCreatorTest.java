package texuna.test.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import texuna.test.models.TableModel;
import texuna.test.models.UserModel;
import texuna.test.util.RowCreator;
import texuna.test.util.SaveReport;
import texuna.test.util.SourceDataParser;
import texuna.test.xml.SettingsParser;
/**
 *  ласс отвечающий за тестирование создани€ строки таблицы
 * @author MSnake
 *
 */
public class RowCreatorTest {

    /**
     * @param args
     * @throws UnsupportedEncodingException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File sourceDataFile = new File("data/source-data.tsv");
        File settingsFile = new File("xml/settings.xml");
        TableModel tableM;
        ArrayList<UserModel> userData = new ArrayList<UserModel>();
        SettingsParser settingPars = new SettingsParser(settingsFile);
        tableM = settingPars.getTableData();
        SourceDataParser dataPars = new SourceDataParser(sourceDataFile);
        try {
            userData = dataPars.createDataUser();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Ќе найден файл с исходными данными пользователей "+sourceDataFile);
        }
        
        RowCreator rowC = new RowCreator(tableM, null);
        ArrayList<String> list = new ArrayList<String>();
        list.add(tableM.getColumnList().get(0).getName());
        list.add(tableM.getColumnList().get(1).getName());
        list.add(tableM.getColumnList().get(2).getName());
        SaveReport.writeToFile(rowC.createRow(list),"out.txt");

    }

}
