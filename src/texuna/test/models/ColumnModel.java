package texuna.test.models;


/**
 * ������ ������� � �������
 * @author MSnake
 *
 */
public class ColumnModel {
    
    private int width; // ������ ������
    private String name; //��������� ������
    
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name.trim();
    }
    

}
