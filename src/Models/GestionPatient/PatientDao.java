package Models.GestionPatient;

import java.util.ArrayList;

public interface PatientDao {
    public void createPatient(Patient patient);
    public Patient getPatientById(int patientId);
    public ArrayList<Patient> getAllPatients();
    public void updatePatient(Patient patient);
    public void deletePatient(int patientId);
}
