package clases;

public class Ejercicios {
    private String id, typetherapy, mac, date, interactions;

    public Ejercicios(String id, String typetherapy, String mac, String date, String interactions) {
        this.id = id;
        this.typetherapy = typetherapy;
        this.mac = mac;
        this.date = date;
        this.interactions = interactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypetherapy() {
        return typetherapy;
    }

    public void setTypetherapy(String typetherapy) {
        this.typetherapy = typetherapy;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

}
