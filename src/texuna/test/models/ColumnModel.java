package texuna.test.models;


/**
 * ћодель колонки в таблице
 * @author MSnake
 *
 */
public class ColumnModel {
    
    private int width; // ширина €чейки
    private String name; //заголовок €чейки
    
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
