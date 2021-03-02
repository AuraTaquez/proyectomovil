package clases;

public class PrescriptionModel {
    String id,typetheraphy,device,date,status,commentary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypetheraphy() {
        return typetheraphy;
    }

    public void setTypetheraphy(String typetheraphy) {
        this.typetheraphy = typetheraphy;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommentary() {
        return commentary;
    }

    public PrescriptionModel(String id, String typetheraphy, String device, String date, String status, String commentary) {
        this.id = id;
        this.typetheraphy = typetheraphy;
        this.device = device;
        this.date = date;
        this.status = status;
        this.commentary = commentary;
    }
    public PrescriptionModel() {

    }
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}


