package texuna.test.xml;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import texuna.test.models.ColumnModel;
import texuna.test.models.TableModel;

/**
 * ����� ���������� �� ������� ����� ��������
 * @author MSnake
 *
 */
public class SettingsParser {
    
    private File filePath; // ���� � ������� �������
    private SAXBuilder parser = new SAXBuilder(); // ������
    private TableModel model; // ������ �������
    
    /**
     * �����������
     * @param filePath - ���� � ������� ������� ���� {@link File}
     */
    public SettingsParser(File filePath) {
        this.filePath = filePath;
        model = new TableModel();
        parseXML();
    }
    
    /**
     * ����� ��������� XML �����
     */
    private void parseXML()
    {
        parser = new SAXBuilder();
        try {
            Document rDoc = parser.build(filePath);
            List<Element> elements = rDoc.getRootElement().getContent(new ElementFilter("page"));     
            
            Iterator iterator = elements.iterator();
            while(iterator.hasNext()){          
                Element page = (Element)iterator.next();
                int w = Integer.parseInt(page.getChildText("width"));           
                int h = Integer.parseInt(page.getChildText("height"));
                
                model.setHeight(h);
                model.setWidth(w);

            }
            Element columnsElement = rDoc.getRootElement().getChild("columns");
            elements = columnsElement.getContent(new ElementFilter("column"));
            iterator =   elements.iterator();
            
            while(iterator.hasNext()){          
                Element column = (Element)iterator.next();
                ColumnModel colMod = new ColumnModel();
                int w = Integer.parseInt(column.getChildText("width"));           
                String str = column.getChildText("title");
                colMod.setName(str);
                colMod.setWidth(w);
                model.addColumb(colMod);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public TableModel getTableData (){
        return model;
    }

}
