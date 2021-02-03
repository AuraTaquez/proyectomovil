package clases;

public class DispositiveModel {
    String id, mac, dispositive, state, observations;

    public DispositiveModel() {    }

    public DispositiveModel(String id, String mac, String dispositive, String state, String observations) {
        this.id = id;
        this.mac = mac;
        this.dispositive = dispositive;
        this.state = state;
        this.observations = observations;
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

    public String getDispositive() {
        return dispositive;
    }

    public void setDispositive(String dispositive) {
        this.dispositive = dispositive;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
