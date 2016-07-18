package texuna.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Класс отвечающий за сохранение информации в текстовый документ с кодировкой UTF-16
 * @author MSnake
 *
 */
public class SaveReport {
    
    /**
     * Метод записи информации в файл
     * @param list список строк для записи типа {@link ArrayList}
     * @param filePath - путь к файлу сохранения типа {@link String}
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void writeToFile(ArrayList<String> list, String filePath) throws FileNotFoundException, UnsupportedEncodingException{
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-16"));
        try {
            for(String str:list)
              {
                  bufferedWriter.append(str+"\n");
              }
            bufferedWriter.flush();
        } catch (IOException ex) {
            // handle exception
        } finally { // закрытие ресурсов обязательно в finally
            // Оба вызова обязательно в отдельных try-catch
            try {
                bufferedWriter.close();
            } catch (IOException ex) {

            }
            
            try {
                fileOutputStream.close();
            } catch (IOException ex) {

            }
        }
    }

}
