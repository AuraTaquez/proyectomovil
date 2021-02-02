package clases;

public class Usuario {
    String address="", birthdate="", cid="", email="", pass = "", names="", surnames="", phone="", role = "";

    public Usuario(String address, String birthdate, String cid, String email, String pass, String names, String surnames, String pone, String role) {
        this.address = address;
        this.birthdate = birthdate;
        this.cid = cid;
        this.email = email;
        this.pass = pass;
        this.names = names;
        this.surnames = surnames;
        this.phone = pone;
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String pone) {
        this.phone = pone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
