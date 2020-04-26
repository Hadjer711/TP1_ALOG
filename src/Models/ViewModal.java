package Models;

public class ViewModal {

    String id;
    String nom, prenom, heure, objet, infoMedicale;

    public ViewModal(String id, String nom, String prenom, String heure, String objet, String infoMedicale) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.heure = heure;
        this.objet = objet;
        this.infoMedicale = infoMedicale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getInfo() {
        return infoMedicale;
    }

    public void setInfo(String info) {
        this.infoMedicale = info;
    }
}
