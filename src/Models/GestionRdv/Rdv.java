package Models.GestionRdv;

import Models.GestionPatient.Patient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Rdv {
    private int rdvId;
    private Date date;
    private Time heure;
    private String objet;
    private Patient patient;
    private static ArrayList<Rdv> rdvs;

    public Rdv(){

    }
    public Rdv( Date date, Time heure, String objet, Patient patient) {
        this.date = date;
        this.heure = heure;
        this.objet = objet;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public static ArrayList<Rdv> listRdvs(Date jour) {
        ArrayList<Rdv> result = new ArrayList<Rdv>();
        for (Rdv r:rdvs) {
            if (r.date==jour){
                result.add(r);
            }
        }
        return result;
    }

    public int getRdvId() {
        return rdvId;
    }

    public void setRdvId(int rdvId) {
        this.rdvId = rdvId;
    }
}
