package texuna.test.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import texuna.test.models.TableModel;
import texuna.test.models.UserModel;
import texuna.test.test.DataParserTest;
import texuna.test.util.SaveReport;
import texuna.test.util.SourceDataParser;
import texuna.test.util.TableCreator;
import texuna.test.xml.SettingsParser;

/**
 * Класс отвечащий за генерацию отчетной таблицы
 * @author MSnake
 *
 */
public class Generator {
    
    private TableModel tableM;  // модель таблицы
    private ArrayList<UserModel> userData; // список моделей "пользователей"
    
    /**
     * Конструктор
     * @param settingsPath - файл настроек типа {@link File}
     * @param sourceDataPath - файл с входными данными типа {@link File}
     */
    public Generator(File settingsPath, File sourceDataPath)
    {
        init(settingsPath,sourceDataPath);
    }
    
    /**
     * Иницилизация входных данных
     * @param settingsFile - файл настроек типа {@link File}
     * @param sourceDataFile - файл с входными данными типа {@link File}
     */
    private void init(File settingsFile, File sourceDataFile)
    {
        SettingsParser settingPars = new SettingsParser(settingsFile);
        tableM = settingPars.getTableData();
        SourceDataParser dataPars = new SourceDataParser(sourceDataFile);
        try {
            userData = dataPars.createDataUser();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл с исходными данными пользователей "+sourceDataFile);
        }
    }
    
    /**
     * Метод генерация отчета
     * @return
     */
    public ArrayList<String> generateReport()
    {
        TableCreator tCreator = new TableCreator(tableM, userData);
        return tCreator.createTable();
    }
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File sourceDataFile = new File(args[1]);
        File settingsFile = new File(args[0]);
        String reportPath = args[2];
        Generator gen = new Generator(settingsFile, sourceDataFile);
        SaveReport.writeToFile(gen.generateReport(), reportPath);
    }

}
