package Models.GestionRdv;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Rdv {
    private int rdvId;
    private LocalDate date;
    private LocalTime heure;
    private String objet;
    private int patientId;

    public Rdv(){

    }
    public Rdv( LocalDate date, LocalTime heure, String objet, int patientId) {
        this.date = date;
        this.heure = heure;
        this.objet = objet;
        this.patientId= patientId;
    }

    public Rdv(LocalDate date, LocalTime heure, String objet) {
        this.date = date;
        this.heure = heure;
        this.objet = objet;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
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