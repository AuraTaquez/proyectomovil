package clases;

public class PrescriptionModel {
    String Id, Device, Pacient, Status, typeTherapy, Commentary, date;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String id, String Pacient, String Device, String status, String typeTherapy, String commentary, String date) {
        Id = id;
        this.Pacient = Pacient;
        this.Device = Device;
        this.Status = status;
        this.typeTherapy = typeTherapy;
        this.Commentary = commentary;
        this.date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        this.Device = device;
    }

    public String getPacient() {
        return Pacient;
    }

    public void setPacient(String pacient) {
        this.Pacient = pacient;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTypeTherapy() {
        return typeTherapy;
    }

    public void setTypeTherapy(String typeTherapy) {
        this.typeTherapy = typeTherapy;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


