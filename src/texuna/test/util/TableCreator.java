package texuna.test.util;

import java.util.ArrayList;
import java.util.ListIterator;


import texuna.test.models.TableModel;
import texuna.test.models.UserModel;

/**
 *  ласс отвечающий за создани€ отчетной таблицы
 * @author MSnake
 *
 */
public class TableCreator {
    
    private TableModel tableModel;  // модель таблицы
    private ArrayList<UserModel> usersData; // список моделей "пользователей"
    
    /**
     *  онструктор 
     * @param table - модель таблицы типа {@link TableModel}
     * @param users - список входных данных типа {@link UserModel}
     */
    public TableCreator (TableModel table, ArrayList<UserModel> users){
        tableModel = table;
        usersData = users;
        
    }
    
    /**
     * ћетод создани€ отчетной таблицы
     * @return - список строк таблицы типа {@link ArrayList}
     */
    public  ArrayList<String> createTable(){
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> head = createHeaderTable();
        boolean endPage = false;
        int h = head.size();
        for (String str: head)
        {
            result.add(str);
            
        }
        ListIterator<UserModel> iterator = usersData.listIterator();
        while (iterator.hasNext()) 
        {
            UserModel user = iterator.next();

            ArrayList<String> rowData = new ArrayList<String>();
            rowData.add(user.getId());
            rowData.add(user.getDate());
            rowData.add(user.getFio());
            
            RowCreator rowCr = new RowCreator(tableModel,user);
            ArrayList<String> newRow = rowCr.createRow(rowData);
            
            int hFuture = h + newRow.size()+1;
            if (hFuture <= tableModel.getHeight())
            {
                for (String str: newRow)
                {
                    result.add(str);
                
                }
                result.add(createSplitRow());
                h = hFuture;
            }
            else
            {
                result.add("~");
                h=head.size();
                for (String str: head)
                {
                    result.add(str);
                   
                }
                for (String str: newRow)
                {
                    result.add(str);
                
                }
                result.add(createSplitRow());
            }
        }
        return result;
    }
    
    /**
     * ћетод создани€ заголовка таблицы
     * @return список строк заголовка таблицы типа {@link ArrayList}
     */
    private ArrayList<String> createHeaderTable()
    {
        ArrayList<String> result = new ArrayList<String>();
        RowCreator row = new RowCreator(tableModel, null);
        ArrayList<String> rowData = new ArrayList<String>();
        
        rowData.add(tableModel.getColumnList().get(0).getName());
        rowData.add(tableModel.getColumnList().get(1).getName());
        rowData.add(tableModel.getColumnList().get(2).getName());
        result.add(createSplitRow());
        for (String r: row.createRow(rowData))
        {
            result.add(r);
        }
        result.add(createSplitRow());
        return result;
        
    }
    

    /**
     * ћетод создани€ разделительной черты между строками таблицы
     * @return - разделительна€ строка типа  {@link String}
     */
    private String createSplitRow(){
        String result="";
        for (int i=0;i < tableModel.getWidth();i++)
        {
            result = result+"-";
        }
        
        return result;
    }

}
