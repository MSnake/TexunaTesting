package texuna.test.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import texuna.test.models.UserModel;

/**
 * Парсер входных данных с информацией о пользователях
 * @author MSnake
 *
 */
public class SourceDataParser {
    
    private File sourceDataFile; // файл с входыми данными
    
    /**
     * Конструктор
     * @param sourceDataFile - файл с входыми данными типа {@link File}
     */
    public SourceDataParser (File sourceDataFile)
    {
        this.sourceDataFile = sourceDataFile;
    }
    
    /**
     * Метод создания списка "пользователей"
     * @return список "пользователей" типа {@link ArrayList}
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
              System.out.println("Не найден файл с исходными данными пользователей "+sourceDataFile);
          }

        return result;
    }
    
 

}
