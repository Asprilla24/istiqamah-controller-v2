package id.co.aladine.istiqamahcontroller.model;

/**
 * Created by aladhine on 21/01/18.
 */

public class PercentageModel {
    private int id;
    private int idSholat;
    private String date;
    private Double percentage;

    public PercentageModel(int id, int idSholat, String date, Double percentage){
        this.id = id;
        this.idSholat = idSholat;
        this.date = date;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSholat() {
        return idSholat;
    }

    public void setIdSholat(int idSholat) {
        this.idSholat = idSholat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
