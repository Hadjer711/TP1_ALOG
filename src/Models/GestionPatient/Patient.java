package Models.GestionPatient;

import Models.GestionRdv.Rdv;

import java.util.ArrayList;

public class Patient {
    private int patientId;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;
    private String infoMedicale;

    public Patient(){

    }

    public Patient(String nom, String prenom, String adresse, String telephone, String mail, String infoMedicale) {

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.infoMedicale = infoMedicale;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInfoMedicale() {
        return infoMedicale;
    }

    public void setInfoMedicale(String infoMedicale) {
        this.infoMedicale = infoMedicale;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
