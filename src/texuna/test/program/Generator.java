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
 * ����� ��������� �� ��������� �������� �������
 * @author MSnake
 *
 */
public class Generator {
    
    private TableModel tableM;  // ������ �������
    private ArrayList<UserModel> userData; // ������ ������� "�������������"
    
    /**
     * �����������
     * @param settingsPath - ���� �������� ���� {@link File}
     * @param sourceDataPath - ���� � �������� ������� ���� {@link File}
     */
    public Generator(File settingsPath, File sourceDataPath)
    {
        init(settingsPath,sourceDataPath);
    }
    
    /**
     * ������������ ������� ������
     * @param settingsFile - ���� �������� ���� {@link File}
     * @param sourceDataFile - ���� � �������� ������� ���� {@link File}
     */
    private void init(File settingsFile, File sourceDataFile)
    {
        SettingsParser settingPars = new SettingsParser(settingsFile);
        tableM = settingPars.getTableData();
        SourceDataParser dataPars = new SourceDataParser(sourceDataFile);
        try {
            userData = dataPars.createDataUser();
        } catch (FileNotFoundException e) {
            System.out.println("�� ������ ���� � ��������� ������� ������������� "+sourceDataFile);
        }
    }
    
    /**
     * ����� ��������� ������
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
