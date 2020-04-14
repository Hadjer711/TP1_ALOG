package Models.GestionRdv;

import java.sql.Date;
import java.sql.Time;

public class Rdv {
    private int rdvId;
    private Date date;
    private Time heure;
    private String objet;
    private int patientId;

    public Rdv(){

    }
    public Rdv( Date date, Time heure, String objet, int patientId) {
        this.date = date;
        this.heure = heure;
        this.objet = objet;
        this.patientId= patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRdvId() {
        return rdvId;
    }

    public void setRdvId(int rdvId) {
        this.rdvId = rdvId;
    }
}
