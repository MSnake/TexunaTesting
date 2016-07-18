package texuna.test.util;

import java.util.ArrayList;
import java.util.ListIterator;

import texuna.test.models.ColumnModel;
import texuna.test.models.TableModel;
import texuna.test.models.UserModel;

/**
 * ����� ��������� �� �������� ������ �������
 * @author MSnake
 *
 */
public class RowCreator {
    private TableModel table;
    private UserModel user;
    
    /**
     * ����������� 
     * @param table - ������ ������� ���� {@link TableModel}
     * @param user - ������� ������ ��� ������� ���� {@link UserModel}
     */
    public RowCreator(TableModel table, UserModel user)
    {
        this.table=table;
        this.user=user;
        
    }
    
    /**
     * ����� �������� ������ ��� �������
     * @param dataList - ������ ����� ����������� ��� ���������� � ������ ������� ���� {@link ArrayList}
     * @return - ������ ����� �������� � ������ ������� ���� {@link ArrayList}
     */
    public ArrayList<String> createRow(ArrayList<String> dataList){
        ArrayList<String> result = new ArrayList<String>();
        
        //�������� ����� �������
        ArrayList<String> idList = new ArrayList<String>();
        ArrayList<String> dateList= new ArrayList<String>();
        ArrayList<String> fioList= new ArrayList<String>();
     
        idList = createCell(table.getColumnList().get(0), dataList.get(0));
        dateList = createCell(table.getColumnList().get(1), dataList.get(1));
        fioList = createCell(table.getColumnList().get(2), dataList.get(2));
        
        //���������� ������������� ���������� ����� � ������������ ������� �������
        ArrayList<Integer> sizeStrings = new ArrayList<Integer>();
        sizeStrings.add(idList.size());
        sizeStrings.add(dateList.size());
        sizeStrings.add(fioList.size());
        int maxS = 0;
        for (Integer s: sizeStrings)
        {
            if (s > maxS) maxS = s;
        }
        
        //���������� ������ ����� � ������ �� ������������� ������� ����� � ������ �������
        idList = updateCell(idList, table.getColumnList().get(0), maxS);
        dateList = updateCell(dateList, table.getColumnList().get(1), maxS);
        fioList = updateCell(fioList, table.getColumnList().get(2), maxS);
        
        //������������ ��������� ������ �������
        for (int i=0;i<maxS;i++)
        {
            String strRes = "| "+idList.get(i)+" | "+dateList.get(i)+" | "+fioList.get(i)+" |";
            result.add(strRes);
        }

        return result;
    }
    
    /**
     * ����� ���������� ������������ ����� �������
     * @param cellList - ������ ����� ��� ���������� ���� {@link ArrayList}
     * @param column - ������ ������, ���������� �� ����� �������� ���� {@link ColumnModel }
     * @param max - ����� ����� � ���������� ������ ������ ������� ���� {@link Integer}
     * @return ����������� ������ ����� ������ ������� ���� {@link ArrayList}
     */
    private ArrayList<String> updateCell(ArrayList<String> cellList,ColumnModel column, int max)
    {
        ArrayList<String> result = new ArrayList<String>();
        int k = max - cellList.size();
        for (String cell: cellList)
        {
            int diffWidth=0;
            if (cell.length()< column.getWidth())
            {
                diffWidth = column.getWidth() - cell.length();
            }
            
            String str=cell;
            for (int i=0; i<diffWidth;i++)
            {
                str=str+" ";
            }
            result.add(str);
        }
        if (k>0)
        {
            for (int i=0;i<k;i++)
            {
                String str = "";
                for (int j=0;j<column.getWidth();j++)
                {
                   str = str+" ";
                }
                result.add(str);
            }
        }
        
        return result;
    }
    
    /**
     * ����� �������� ������ ������ �������
     * @param colModel - ������ ������, ���������� �� ����� �������� ���� {@link ColumnModel }
     * @param text - �����, ������������� � ������ ���� {@link String}
     * @return - ������ ����� ������ ������� ���� {@link ArrayList}
     */
    private ArrayList<String> createCell(ColumnModel colModel, String text)
    {
        ArrayList<String> result = new ArrayList<String>(); 
        result =  splitCellText(text, colModel);
        return result;
        
        
    }
    
    /**
     * ����� ������� ������ �� ���������
     * @param text - �����, ������������� � ������ ���� {@link String}
     * @param colModel - ������ ������, ���������� �� ����� �������� ���� {@link ColumnModel }
     * @return - ������ ����� ���� {@link ArrayList}
     */
    private ArrayList<String> splitCellText(String text,ColumnModel colModel){
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> arrPhrases = new ArrayList<String>(); // ��������� ��������(����)
        StringBuilder stringBuffer = new StringBuilder(); // ����� ��� ���������� �����
        StringBuilder buff = new StringBuilder();
        String [] arrWords = text.split(" ");
        int cnt = 0;   // �������, ����� �� ����� �� ������� ������������� ��������
        int index = 0; // ������ �������� � ������� arrWords. ����� ��������� �� ������ �������
        int leng = arrWords.length; // ����� ���������� ���� (����� �������)
        boolean endStr = true;
        while (index  != leng)
        {
            buff.append(arrWords[index]);
            while (buff.length()>0)
            {
                if (cnt+ buff.toString().length() <= colModel.getWidth())
                {
                    cnt=cnt+buff.length();
                    stringBuffer.append(buff.toString());
                    buff = new StringBuilder();
                    endStr = true;
                    if (stringBuffer.length()<colModel.getWidth())
                    {
                        stringBuffer.append(" ");
                        cnt++;
                        endStr = false;
                    }
                }
                else
                {
                    stringBuffer.append(buff.substring(0, colModel.getWidth()-cnt));
                    String ost = buff.substring(colModel.getWidth()-cnt,buff.length());
                    buff = new StringBuilder(ost);
                    cnt=0;
                    endStr = true;
                }
                
                if (endStr && stringBuffer.length()!=0) 
                {
                    arrPhrases.add(stringBuffer.toString());
                    stringBuffer = new StringBuilder();
                }
            }
            index++;
        }


        if (stringBuffer.length() != 0)
        {
            arrPhrases.add(stringBuffer.toString());
        }
        return arrPhrases;
        
    }
    

}
