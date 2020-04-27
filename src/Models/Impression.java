package Models;

import Models.GestionPatient.Patient;
import Models.GestionPatient.PatientDaoImpl;
import Models.GestionRdv.Rdv;
import Models.GestionRdv.RdvDaoImpl;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;

public class Impression {
    private Rdv rdv=new Rdv();
    private Patient patient=new Patient();
    private PatientDaoImpl patientDao=new PatientDaoImpl();
    private RdvDaoImpl rdvDao=new RdvDaoImpl();

    public void imprimerRdv(Integer id){
          rdv= rdvDao.getRdvById(id);
          patient=patientDao.getPatientById(rdv.getPatientId());

        try {
            File myObj = new File("RDV"+id+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("RDV"+id+".txt");
            myWriter.write("****Information RDV**** \n");
            myWriter.write("Numero rdv:"+ rdv.getRdvId() +"\n");
            myWriter.write("Date :"+ rdv.getDate() +"\n");
            myWriter.write("Heure :"+ rdv.getHeure() +"\n");
            myWriter.write("Objet rdv:"+ rdv.getObjet() +"\n");
            myWriter.write("****Information Patient**** \n");
            myWriter.write("Nom:"+ patient.getNom() +"\n");
            myWriter.write("Prénom:"+ patient.getPrenom()+"\n");
            myWriter.write("Email:"+ patient.getMail() +"\n");
            myWriter.write("Adresse:"+ patient.getAdresse()+"\n");
            myWriter.write("Téléphone:"+ patient.getTelephone() +"\n");
            myWriter.write("Informations Médicale:"+ patient.getInfoMedicale()+"\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
