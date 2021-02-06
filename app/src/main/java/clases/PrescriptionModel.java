package clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrescriptionModel {
    String id,typetheraphy,device,date,therapistemail,image;

    public PrescriptionModel(String id, String typetheraphy, String device, String date, String therapistemail, String image) {
        this.id = id;
        this.typetheraphy = typetheraphy;
        this.device = device;
        this.date = date;
        this.therapistemail = therapistemail;
        this.image = image;
    }
    public PrescriptionModel() {

    }
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

    public String getTherapistemail() {
        return therapistemail;
    }

    public void setTherapistemail(String therapistemail) {
        this.therapistemail = therapistemail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PrescriptionModel(JSONObject a) throws JSONException {
        //id =  a.getString("id");
        typetheraphy =  a.getString("typetheraphy");
        device =  a.getString("iddevice");
        date = a.getString("date");
        therapistemail = a.getString("therapistemail");
        image = a.getString("image");
    }

    public static ArrayList<PrescriptionModel> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<PrescriptionModel> prescription = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            prescription.add(new PrescriptionModel(datos.getJSONObject(i)));
        }
        return prescription;
    }

}


