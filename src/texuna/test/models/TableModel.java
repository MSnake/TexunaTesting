package texuna.test.models;

import java.util.ArrayList;


/**
 * ������ �������
 * @author MSnake
 *
 */
public class TableModel {
    
    private int width; // ������ �������
    private int height; // ������ �������
    private ArrayList<ColumnModel> columnList; // ������ ������� �����
    
    public TableModel()
    {
        columnList = new ArrayList<ColumnModel>();
    }
    
    
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public ArrayList<ColumnModel> getColumnList() {
        return columnList;
    }
    public void setColumnList(ArrayList<ColumnModel> columnList) {
        this.columnList = columnList;
    }
    
    public void addColumb(ColumnModel model) {
        columnList.add(model);
    }
    
    
    
    
    
    

}
