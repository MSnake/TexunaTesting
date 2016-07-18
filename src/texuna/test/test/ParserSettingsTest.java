package texuna.test.test;

import java.io.File;

import texuna.test.models.TableModel;
import texuna.test.xml.SettingsParser;

/**
 * Тестирование работоспособности парсера настроек таблицы
 * @author MSnake
 *
 */
public class ParserSettingsTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File setingFile = new File("xml/settings.xml");
        SettingsParser parser = new SettingsParser(setingFile);
        TableModel mod = new TableModel();
        mod = parser.getTableData();
        System.out.println("");

    }

}
