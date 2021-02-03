package clases;

public class Ejercicios {
    private String typetherapy, device, date, data, hour_inicial, hour_final, image;


    public Ejercicios(String typetherapy, String device, String date, String data, String hour_inicial, String hour_final, String image) {
        this.typetherapy = typetherapy;
        this.device = device;
        this.date = date;
        this.data = data;
        this.hour_inicial = hour_inicial;
        this.hour_final = hour_final;
        this.image = image;
    }

    public String getTypetherapy() {
        return typetherapy;
    }

    public void setTypetherapy(String typetherapy) {
        this.typetherapy = typetherapy;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHour_inicial() {
        return hour_inicial;
    }

    public void setHour_inicial(String hour_inicial) {
        this.hour_inicial = hour_inicial;
    }

    public String getHour_final() {
        return hour_final;
    }

    public void setHour_final(String hour_final) {
        this.hour_final = hour_final;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
