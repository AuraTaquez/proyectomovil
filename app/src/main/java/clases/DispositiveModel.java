package clases;

public class DispositiveModel {
    String id, mac, dispositivo, estado, observaciones;

    public DispositiveModel() {    }

    public DispositiveModel(String id, String mac, String dispositivo, String estado, String observaciones) {
        this.id = id;
        this.mac = mac;
        this.dispositivo = dispositivo;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
