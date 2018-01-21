package id.co.aladine.istiqamahcontroller.model;

/**
 * Created by aladhine on 21/01/18.
 */

public class SholatModel {
    private int id;
    private String name;
    private String datetime;

    public SholatModel(int id, String name, String datetime){
        this.id = id;
        this.name = name;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTime() {
        return datetime.split(" ")[1];
    }
}
