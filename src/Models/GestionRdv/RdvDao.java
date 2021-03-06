package Models.GestionRdv;

import Models.GestionPatient.Patient;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RdvDao {
    public void createRdv(Rdv rdv,Patient patient);
    public Rdv getRdvById(int rdvId);
    public ArrayList<Rdv> getAllRdvsPatient(int patientId);
    public ArrayList<Rdv> getAllRdvsJour(LocalDate jour);

    public void updateRdv(Rdv rdv);
    public void changeDate(int rdvId, LocalDate jour, LocalTime heure);
    public void deleteRdv(int rdvId);
}
