package Models.GestionRdv;

import Models.GestionPatient.Patient;

import java.sql.Date;
import java.util.ArrayList;

public interface RdvDao {
    public void createRdv(Rdv rdv);
    public Rdv getRdvById(int rdvId);
    public ArrayList<Rdv> getAllRdvsPatient(Patient patient);
    public ArrayList<Rdv> getAllRdvsJour(Date jour);
    public void updateRdv(Rdv rdv);
    public void deleteRdv(int rdvId);
}
