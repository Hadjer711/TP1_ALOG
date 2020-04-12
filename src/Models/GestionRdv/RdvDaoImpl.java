package Models.GestionRdv;

import Models.GestionPatient.Patient;
import Models.GestionRdv.RdvDao;

import java.time.LocalDate;
import java.util.ArrayList;

public class RdvDaoImpl implements RdvDao {

    @Override
    public void createRdv(Patient patient, Rdv rdv) {

    }

    @Override
    public Patient getRdvById(int rdvId) {
        return null;
    }

    @Override
    public ArrayList<Rdv> getAllRdvsPatient(Patient patient) {
        return null;
    }

    @Override
    public ArrayList<Rdv> getAllRdvsJour(LocalDate jour) {
        return null;
    }

    @Override
    public void updateRdv(Rdv rdv) {

    }

    @Override
    public void deleteRdv(int rdvId) {

    }
}
