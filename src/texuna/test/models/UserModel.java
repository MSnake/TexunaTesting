package texuna.test.models;


/**
 * Модель содержащая информацию о пользователях
 * @author MSnake
 *
 */
public class UserModel {
    
    private String id; // номер "пользователя"
    private String date; // дата "пользователя"
    private String fio; // ФИО "пользователя"
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }

}
