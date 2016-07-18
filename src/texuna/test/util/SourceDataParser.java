package texuna.test.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import texuna.test.models.UserModel;

/**
 * ������ ������� ������ � ����������� � �������������
 * @author MSnake
 *
 */
public class SourceDataParser {
    
    private File sourceDataFile; // ���� � ������� �������
    
    /**
     * �����������
     * @param sourceDataFile - ���� � ������� ������� ���� {@link File}
     */
    public SourceDataParser (File sourceDataFile)
    {
        this.sourceDataFile = sourceDataFile;
    }
    
    /**
     * ����� �������� ������ "�������������"
     * @return ������ "�������������" ���� {@link ArrayList}
     * @throws FileNotFoundException
     */
    public ArrayList<UserModel> createDataUser() throws FileNotFoundException
    {
        ArrayList<UserModel> result = new ArrayList<UserModel>();
        try {
            Scanner scanner = new Scanner(sourceDataFile, "UTF-16");
            while (scanner.hasNext()) {
              String line = scanner.nextLine();
              String[] splitLine = line.split("\t");
              UserModel um = new UserModel();
              um.setId(splitLine[0]);
              um.setDate(splitLine[1]);
              um.setFio(splitLine[2]);
              result.add(um);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
              System.out.println("�� ������ ���� � ��������� ������� ������������� "+sourceDataFile);
          }

        return result;
    }
    
 

}
