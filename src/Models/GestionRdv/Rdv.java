package Models.GestionRdv;

import java.sql.Date;
import java.sql.Time;

public class Rdv {
<<<<<<< HEAD
    private int id;
    private LocalDate date;
    private LocalTime heure;
    private String objet;
    private Patient patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rdv(int id, LocalDate date, LocalTime heure, String objet, Patient patient) {
        this.id = id;
=======
    private int rdvId;
    private Date date;
    private Time heure;
    private String objet;
    private int patientId;

    public Rdv(){

    }
    public Rdv( Date date, Time heure, String objet, int patientId) {
>>>>>>> 7d16d5ca4e2beb081e0993f14167f0a829061b2c
        this.date = date;
        this.heure = heure;
        this.objet = objet;
        this.patientId= patientId;
    }

<<<<<<< HEAD

    private static ArrayList<Rdv> rdvs;

    public Rdv(LocalDate date, LocalTime heure, String objet) {
        this.date = date;
        this.heure = heure;
        this.objet = objet;

    }

    public LocalDate getDate() {
=======
    public Date getDate() {
>>>>>>> 7d16d5ca4e2beb081e0993f14167f0a829061b2c
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