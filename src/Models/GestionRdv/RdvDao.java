package Models.GestionRdv;

import Models.GestionPatient.Patient;

import java.time.LocalDate;
import java.util.ArrayList;

public interface RdvDao {
    public void createRdv(Patient patient, Rdv rdv);
    public Patient getRdvById(int rdvId);
    public ArrayList<Rdv> getAllRdvsPatient(Patient patient);
    public ArrayList<Rdv> getAllRdvsJour(LocalDate jour);
    public void updateRdv(Rdv rdv);
    public void deleteRdv(int rdvId);
}
